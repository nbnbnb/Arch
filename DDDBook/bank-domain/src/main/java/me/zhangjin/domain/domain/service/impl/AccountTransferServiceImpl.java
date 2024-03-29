package me.zhangjin.domain.domain.service.impl;

import me.zhangjin.domain.domain.entity.Account;
import me.zhangjin.domain.domain.service.AccountTransferService;
import me.zhangjin.domain.domain.dp.ExchangeRate;
import me.zhangjin.domain.domain.dp.Money;

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