package me.zhangjin.bank.types;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ExchangeRate {
    BigDecimal rate;
    Currency from;
    Currency to;

    public ExchangeRate(BigDecimal rate, Currency from, Currency to) {
        this.rate = rate;
        this.from = from;
        this.to = to;
    }

    public Money exchangeTo(Money target) {
        return null;
    }
}
