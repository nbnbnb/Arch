package me.zhangjin.bank.types;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Money {
    BigDecimal amount;
    Currency currency;

    public Money(BigDecimal amount, Currency currency) {
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
