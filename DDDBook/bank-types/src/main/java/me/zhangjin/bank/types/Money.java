package me.zhangjin.bank.types;

import lombok.Value;
import me.zhangjin.bank.exception.InvalidMoneyException;

import java.math.BigDecimal;

@Value
public class Money {
    BigDecimal amount;
    Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        // 做校验
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidMoneyException();
        }

        this.amount = amount;
        this.currency = currency;
    }

    public Money add(Money money) {
        return null;
    }

    public Money subtract(Money money) {
        return null;
    }

    public int compareTo(Money money) {
        return 0;
    }
}
