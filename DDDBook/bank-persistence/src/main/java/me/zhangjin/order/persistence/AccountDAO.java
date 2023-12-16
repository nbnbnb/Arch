package me.zhangjin.order.persistence;

/**
 * <p>DAO 操作的都是 DO
 *
 * <p>将 DO 转换为 Aggregate，通过 Repository 进行存储
 *
 * <p>将 DO 存储到数据库
 *
 * <p>从数据库获取记录转换为 DO
 */
public interface AccountDAO {

    // 每个 DAO 实现接口对应一种类型的数据库

    // DAO 对应的是一个特定的数据库类型的操作，相当于 SQL 的封装
    // 所有操作的对象都是 DO 类
    // 所有接口都可以根据数据库实现的不同而改变
    // 比如，insert 和 update 属于数据库专属的操作

    AccountDO selectById(Long accountId);

    AccountDO selectByAccountNumber(String accountNumber);

    AccountDO selectByUserId(Long userId);

    int insert(AccountDO accountDO);

    int update(AccountDO accountDO);

    int delete(AccountDO accountDO);

}
