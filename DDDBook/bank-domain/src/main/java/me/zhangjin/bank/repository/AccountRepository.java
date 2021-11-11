package me.zhangjin.bank.repository;

import me.zhangjin.bank.domain.entity.Account;
import me.zhangjin.bank.types.AccountId;
import me.zhangjin.bank.types.AccountNumber;
import me.zhangjin.bank.types.UserId;

public interface AccountRepository {

    Account find(AccountId id);

    Account find(AccountNumber accountNumber);

    Account find(UserId userId);

    Account save(Account account);

}
