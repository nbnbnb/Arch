package me.zhangjin.bank.domain.entity;


import me.zhangjin.bank.domain.dp.*;
import me.zhangjin.bank.exception.*;



/**
 * 实体对象是我们正常业务应该用的业务模型，它的字段和方法应该和业务语言保持一致，和持久化方式无关
 * <p>
 * 也就是说，Entity 和 DO 很可能有着完全不一样的字段命名和字段类型，甚至嵌套关系
 * <p>
 * Entity 的生命周期应该仅存在于内存中，不需要可序列化和可持久化
 */
public class Account {

    private AccountId id;
    private AccountNumber accountNumber;
    private UserId userId;
    private Money available;
    private Money dailyLimit;

    public NewCurrency getCurrency() {
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

    public UserId getUserId() {
        return this.userId;
    }

    public AccountNumber getAccountNumber() {
        return this.accountNumber;
    }
}