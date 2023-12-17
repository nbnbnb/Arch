package me.zhangjin.acl.application.impl;

import me.zhangjin.acl.application.TransferService;
import me.zhangjin.acl.command.TransferCommand;
import me.zhangjin.acl.domain.dp.*;
import me.zhangjin.acl.domain.entity.Account;
import me.zhangjin.acl.domain.service.AccountTransferService;
import me.zhangjin.acl.dto.TransferDTO;
import me.zhangjin.acl.message.AuditMessage;
import me.zhangjin.acl.external.ExchangeRateService;
import me.zhangjin.acl.messaging.AuditMessageProducer;
import me.zhangjin.acl.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/*
 * 业务逻辑清晰，数据存储和业务逻辑完全分隔
 *
 * Entity、Domain Primitive、Domain Service 都是独立的对象，没有任何外部依赖，但是却包含了所有核心业务逻辑，可以单独完整测试
 *
 * 原有的 TransferService 不再包括任何计算逻辑，仅仅作为组件编排，所有逻辑均 delegate 到其他组件
 *
 * 这种仅包含 Orchestration（编排）的服务叫做 Application Service（应用服务）
 */

// 负责组件编排的 Application Service
// 这些服务仅仅依赖了一些抽象出来的 ACL（Anti-Corruption Layer） 类和 Repository 类，而其具体实现类是通过依赖注入注进来的
@Component
public class TransferServiceImpl implements TransferService {

    // ACL - Repository
    @Autowired
    private AccountRepository accountRepository;

    // ACL - 抽象中间件
    @Autowired
    private AuditMessageProducer auditMessageProducer;

    // ACL - 外部服务
    @Autowired
    private ExchangeRateService exchangeRateService;

    // 领域服务
    @Autowired
    private AccountTransferService accountTransferService;

    @Override
    public TransferDTO transfer(TransferCommand transferCommand) {

        BigDecimal targetAmount = transferCommand.getTargetAmount();
        String targetCurrency = transferCommand.getTargetCurrency();
        Long sourceUserId = transferCommand.getSourceUserId();
        String targetAccountNumber = transferCommand.getTargetAccountNumber();

        // 参数校验
        // Domain Primitive
        Money targetMoney = new Money(targetAmount, new NewCurrency(targetCurrency));

        // 访问 bank-persistence
        // 接口能力是在 Domain 中提供的
        // 读数据
        Account sourceAccount = accountRepository.find(new UserId(sourceUserId));
        Account targetAccount = accountRepository.find(new AccountNumber(targetAccountNumber));
        ExchangeRate exchangeRate = exchangeRateService.getExchangeRate(sourceAccount.getCurrency(), targetMoney.getCurrency());

        // 访问 bank-domain
        // 业务逻辑
        // 业务逻辑内部修改 sourceAccount 和 targetAccount
        accountTransferService.transfer(sourceAccount, targetAccount, targetMoney, exchangeRate);

        // 访问 bank-persistence
        // 接口能力是在 domain 中提供的
        // 保存数据
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        // 访问 bank-messaging
        // 接口能力是在 domain 中提供的
        // 发送审计消息
        AuditMessage message = new AuditMessage(sourceUserId,
                sourceAccount.getAccountNumber().getValue(),
                targetAccount.getAccountNumber().getValue(),
                targetMoney.getValue(), new Date());

        auditMessageProducer.send(message);

        TransferDTO res = new TransferDTO();
        res.setSuccess(true);
        res.setMessage("");

        return res;

    }

}
