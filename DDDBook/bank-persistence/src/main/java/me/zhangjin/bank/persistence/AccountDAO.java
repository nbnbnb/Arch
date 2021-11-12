package me.zhangjin.bank.persistence;

import me.zhangjin.bank.domain.entity.Account;

/**
 * 将 DO 存储到数据库
 * <p>
 * 从数据库获取记录转换为 DO
 */
public interface AccountDAO {

    AccountDO selectById(Long accountId);

    AccountDO selectByAccountNumber(String accountNumber);

    AccountDO selectByUserId(Long userId);

    int insert(AccountDO accountDO);

    int update(AccountDO accountDO);

}
