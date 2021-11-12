package me.zhangjin.bank.repository.impl;

import me.zhangjin.bank.domain.entity.Account;
import me.zhangjin.bank.persistence.AccountBuilder;
import me.zhangjin.bank.persistence.AccountDO;
import me.zhangjin.bank.persistence.AccountDAO;
import me.zhangjin.bank.repository.AccountRepository;
import me.zhangjin.bank.domain.dp.AccountId;
import me.zhangjin.bank.domain.dp.AccountNumber;
import me.zhangjin.bank.domain.dp.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountBuilder accountBuilder;

    @Override
    public Account find(AccountId id) {
        AccountDO accountDO = accountDAO.selectById(id.getValue());
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public Account find(AccountNumber accountNumber) {
        AccountDO accountDO = accountDAO.selectByAccountNumber(accountNumber.getValue());
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public Account find(UserId userId) {
        AccountDO accountDO = accountDAO.selectByUserId(userId.getValue());
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public Account save(Account account) {
        AccountDO accountDO = accountBuilder.fromAccount(account);
        if (accountDO.getId() == null) {
            accountDAO.insert(accountDO);
        } else {
            accountDAO.update(accountDO);
        }
        return accountBuilder.toAccount(accountDO);
    }

}
