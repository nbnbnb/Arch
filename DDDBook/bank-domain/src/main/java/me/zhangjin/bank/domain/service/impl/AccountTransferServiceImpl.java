package me.zhangjin.bank.domain.service.impl;

import me.zhangjin.bank.domain.entity.Account;
import me.zhangjin.bank.domain.service.AccountTransferService;
import me.zhangjin.bank.types.ExchangeRate;
import me.zhangjin.bank.types.Money;

public class AccountTransferServiceImpl implements AccountTransferService {

    @Override
    public void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate) {
        // 注意：ExchangeRate 外部依赖是传递进来的
        Money sourceMoney = exchangeRate.exchangeTo(targetMoney);

        // 执行业务逻辑
        // 更改实体状态值
        sourceAccount.deposit(sourceMoney);
        targetAccount.withdraw(targetMoney);

    }

}