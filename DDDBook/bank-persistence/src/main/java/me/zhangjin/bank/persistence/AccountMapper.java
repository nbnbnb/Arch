package me.zhangjin.bank.persistence;

import me.zhangjin.bank.domain.entity.Account;

public interface AccountMapper {

    AccountDO selectById(Long accountId);

    AccountDO selectByAccountNumber(String accountNumber);

    AccountDO selectByUserId(Long userId);

    int insert(AccountDO accountDO);

    int update(AccountDO accountDO);
}
