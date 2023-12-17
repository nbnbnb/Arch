package me.zhangjin.acl.repository;

import me.zhangjin.acl.domain.entity.Account;
import me.zhangjin.acl.domain.dp.AccountId;
import me.zhangjin.acl.domain.dp.AccountNumber;
import me.zhangjin.acl.domain.dp.UserId;

// 通过 Repository，改变业务代码的思维方式，让业务逻辑不再面向数据库编程，而是面向领域模型编程
// Repository 作为一个接口类，可以比较容易的实现 Mock 或 Stub，可以很容易测试

// 通过 Account 对象，避免了其他业务逻辑代码和数据库的直接耦合
// 避免了当数据库字段变化时，大量业务逻辑也跟着变的问题

public interface AccountRepository extends Repository<Account, AccountId> {

    // 添加扩展的接口

    Account find(AccountNumber accountNumber);

    Account find(UserId userId);

}
