package me.zhangjin.domain.domain.dp;

import lombok.Value;

import java.math.BigDecimal;

/**
 * 涉及到多个对象的业务逻辑，需要用 DP 包装掉
 * <p>
 * 这里引出 DP 的第三个原则：封装多对象行为
 */
@Value
public class ExchangeRate {
    BigDecimal rate;
    NewCurrency from;
    NewCurrency to;

    public ExchangeRate(BigDecimal rate, NewCurrency from, NewCurrency to) {

        if (rate == null) {
            throw new IllegalArgumentException("rate is null");
        }

        if (rate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("rate < 0");
        }

        this.rate = rate;
        this.from = from;
        this.to = to;
    }

    public Money exchangeTo(Money target) {
        return null;
    }
}
