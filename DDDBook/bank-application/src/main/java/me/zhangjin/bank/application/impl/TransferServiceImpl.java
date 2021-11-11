package me.zhangjin.bank.application.impl;

import me.zhangjin.bank.application.TransferService;
import me.zhangjin.bank.domain.entity.Account;
import me.zhangjin.bank.domain.service.AccountTransferService;
import me.zhangjin.bank.domain.types.AuditMessage;
import me.zhangjin.bank.external.ExchangeRateService;
import me.zhangjin.bank.messaging.AuditMessageProducer;
import me.zhangjin.bank.repository.AccountRepository;
import me.zhangjin.bank.types.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;


@Component
public class TransferServiceImpl implements TransferService {

    private AccountRepository accountRepository;
    private AuditMessageProducer auditMessageProducer;
    private ExchangeRateService exchangeRateService;
    private AccountTransferService accountTransferService;

    @Override
    public Result<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency) {

        // 参数校验
        Money targetMoney = new Money(targetAmount, new Currency(targetCurrency));

        // 读数据
        Account sourceAccount = accountRepository.find(new UserId(sourceUserId));
        Account targetAccount = accountRepository.find(new AccountNumber(targetAccountNumber));
        ExchangeRate exchangeRate = exchangeRateService.getExchangeRate(sourceAccount.getCurrency(), targetMoney.getCurrency());

        // 业务逻辑
        accountTransferService.transfer(sourceAccount, targetAccount, targetMoney, exchangeRate);

        // 保存数据
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        // 发送审计消息
        AuditMessage message = new AuditMessage(new UserId(sourceUserId), sourceAccount, targetAccount, targetMoney, new Date());
        auditMessageProducer.send(message);

        return Result.success(true);

    }
}
