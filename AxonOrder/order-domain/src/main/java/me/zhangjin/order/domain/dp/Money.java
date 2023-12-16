package me.zhangjin.order.domain.dp;

import lombok.Data;
import me.zhangjin.order.exception.InvalidMoneyException;

import java.math.BigDecimal;


@Data
public class Money {
    BigDecimal amount;
    NewCurrency currency;

    public Money(BigDecimal amount, NewCurrency currency) {

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

    public NewCurrency getCurrency() {
        return currency;
    }

    public BigDecimal getValue() {
        return this.amount;
    }
}
