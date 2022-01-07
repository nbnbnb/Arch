package me.zhangjin.bank.repository.impl;

import me.zhangjin.bank.domain.entity.Account;
import me.zhangjin.bank.converter.AccountConverter;
import me.zhangjin.bank.persistence.AccountDO;
import me.zhangjin.bank.persistence.AccountDAO;
import me.zhangjin.bank.repository.AccountRepository;
import me.zhangjin.bank.domain.dp.AccountId;
import me.zhangjin.bank.domain.dp.AccountNumber;
import me.zhangjin.bank.domain.dp.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// AccountRepositoryImpl 实现类，由于其职责被单一出来
// 只需要关注 Account 到 AccountDO 的映射关系和 Repository 方法到 DAO 方法之间的映射关系，相对于来说更容易测试

@Component
public class AccountRepositoryImpl implements AccountRepository {

    // Repository 对应的是 Entity 对象读取储存的抽象，在接口层面做统一，不关注底层实现
    // 比如，通过 save 保存一个 Entity 对象，但至于具体是 insert 还是 update 并不关心

    // Repository 的具体实现类通过调用 DAO 来实现各种操作，通过 Builder/Factory/Converter 对象实现 AccountDO 到 Account 之间的转化

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountConverter accountConverter;

    @Override
    public Account find(AccountId id) {
        AccountDO accountDO = accountDAO.selectById(id.getValue());
        return accountConverter.toEntity(accountDO);
    }

    @Override
    public Account find(AccountNumber accountNumber) {
        AccountDO accountDO = accountDAO.selectByAccountNumber(accountNumber.getValue());
        return accountConverter.toEntity(accountDO);
    }

    @Override
    public Account find(UserId userId) {
        AccountDO accountDO = accountDAO.selectByUserId(userId.getValue());
        return accountConverter.toEntity(accountDO);
    }

    @Override
    public Account save(Account account) {
        AccountDO accountDO = accountConverter.toDO(account);
        if (accountDO.getId() == null) {
            accountDAO.insert(accountDO);
        } else {
            accountDAO.update(accountDO);
        }
        return accountConverter.toEntity(accountDO);
    }

}
