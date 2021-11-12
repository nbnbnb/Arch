package me.zhangjin.bank.persistence;

import me.zhangjin.bank.domain.entity.Account;

/**
 * DAO 操作的都是 DO
 * <p>
 * 将 DO 转换为 Aggregate，通过 Repository 进行存储
 * <p>
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
