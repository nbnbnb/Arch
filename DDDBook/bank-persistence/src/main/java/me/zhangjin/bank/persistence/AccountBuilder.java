package me.zhangjin.bank.persistence;

import me.zhangjin.bank.domain.entity.Account;

/**
 * DO 和 Entity 转换器
 */
public interface AccountBuilder {
    Account toAccount(AccountDO accountDO);

    AccountDO fromAccount(Account account);
}
