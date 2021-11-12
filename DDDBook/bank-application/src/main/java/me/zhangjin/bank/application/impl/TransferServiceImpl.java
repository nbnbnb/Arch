package me.zhangjin.bank.application.impl;

import me.zhangjin.bank.application.TransferService;
import me.zhangjin.bank.domain.dp.*;
import me.zhangjin.bank.domain.entity.Account;
import me.zhangjin.bank.domain.service.AccountTransferService;
import me.zhangjin.bank.message.AuditMessage;
import me.zhangjin.bank.external.ExchangeRateService;
import me.zhangjin.bank.messaging.AuditMessageProducer;
import me.zhangjin.bank.repository.AccountRepository;
import me.zhangjin.bank.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;


@Component
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuditMessageProducer auditMessageProducer;

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private AccountTransferService accountTransferService;

    @Override
    public Result<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency) {

        // 参数校验
        // Domain Primitive
        Money targetMoney = new Money(targetAmount, new NewCurrency(targetCurrency));

        // 访问 bank-persistence
        // 接口能力是在 domain 中提供的
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
        AuditMessage message = new AuditMessage(sourceAccount.getUserId().getSourceUserId(),
                sourceAccount.getAccountNumber().getAccountNumber(),
                targetAccount.getAccountNumber().getAccountNumber(), targetMoney.getAmount(), new Date());

        auditMessageProducer.send(message);

        return Result.success(true);

    }

}
