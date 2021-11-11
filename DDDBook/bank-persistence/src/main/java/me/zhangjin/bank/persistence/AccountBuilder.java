package me.zhangjin.bank.persistence;

import me.zhangjin.bank.domain.entity.Account;

public interface AccountBuilder {
    Account toAccount(AccountDO accountDO);

    AccountDO fromAccount(Account account);
}
