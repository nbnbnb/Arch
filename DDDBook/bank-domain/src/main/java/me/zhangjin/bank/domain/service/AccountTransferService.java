package me.zhangjin.bank.domain.service;

import me.zhangjin.bank.domain.entity.Account;
import me.zhangjin.bank.domain.dp.ExchangeRate;
import me.zhangjin.bank.domain.dp.Money;

/**
 * 领域服务
 * <p>
 * Entity 够解决基于单个对象的逻辑变更
 * <p>
 * DomainService 解决多个对象间的业务逻辑变更
 */
public interface AccountTransferService {
    void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate);
}
