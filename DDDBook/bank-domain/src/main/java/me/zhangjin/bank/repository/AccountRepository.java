package me.zhangjin.bank.repository;

import me.zhangjin.bank.domain.entity.Account;
import me.zhangjin.bank.domain.dp.AccountId;
import me.zhangjin.bank.domain.dp.AccountNumber;
import me.zhangjin.bank.domain.dp.UserId;


public interface AccountRepository {

    Account find(AccountId id);

    Account find(AccountNumber accountNumber);

    Account find(UserId userId);

    Account save(Account account);

}
