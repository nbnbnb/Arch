package com.alibaba.demo.domain.dp;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Currency;

@Value
public class Money {
    // @Value 注解自动添加 private final 属性
    BigDecimal amount;
    Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }
}
