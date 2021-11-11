package me.zhangjin.bank.domain.service.impl;

import me.zhangjin.bank.domain.entity.Account;
import me.zhangjin.bank.domain.service.AccountTransferService;
import me.zhangjin.bank.external.ExchangeRateService;
import me.zhangjin.bank.types.ExchangeRate;
import me.zhangjin.bank.types.Money;

public class AccountTransferServiceImpl implements AccountTransferService {

    @Override
    public void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate) {
        Money sourceMoney = exchangeRate.exchangeTo(targetMoney);
        sourceAccount.deposit(sourceMoney);
        targetAccount.withdraw(targetMoney);
    }
}