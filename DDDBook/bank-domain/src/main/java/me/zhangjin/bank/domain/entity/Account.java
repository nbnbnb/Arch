package me.zhangjin.bank.domain.entity;

import lombok.Data;
import me.zhangjin.bank.exception.DailyLimitExceededException;
import me.zhangjin.bank.exception.InsufficientFundsException;
import me.zhangjin.bank.exception.InvalidCurrencyException;
import me.zhangjin.bank.types.*;

@Data
public class Account {

    private AccountId id;
    private AccountNumber accountNumber;
    private UserId userId;
    private Money available;
    private Money dailyLimit;

    public Currency getCurrency() {
        return this.available.getCurrency();
    }

    // 转入
    public void deposit(Money money) {
        if (!this.getCurrency().equals(money.getCurrency())) {
            throw new InvalidCurrencyException();
        }
        this.available = this.available.add(money);
    }

    // 转出
    public void withdraw(Money money) {
        if (this.available.compareTo(money) < 0) {
            throw new InsufficientFundsException();
        }
        if (this.dailyLimit.compareTo(money) < 0) {
            throw new DailyLimitExceededException();
        }
        this.available = this.available.subtract(money);
    }

}