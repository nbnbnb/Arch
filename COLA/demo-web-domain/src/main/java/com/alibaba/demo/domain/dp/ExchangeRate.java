package com.alibaba.demo.domain.dp;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * 涉及到多个对象的业务逻辑，需要用 DP 包装掉
 */
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

    public Money exchange(Money fromMoney) {
        // notNull(fromMoney);
        // isTrue(this.from.equals(fromMoney.getCurrency()));
        BigDecimal targetAmount = fromMoney.getAmount().multiply(rate);
        return new Money(targetAmount, to);
    }

    // 此操作涉及到多个对象
    public void pay(Money money, Currency targetCurrency, Long recipientId) {
        ExchangeRate rate = null;
        Money targetMoney = rate.exchange(money);

        // BankService 是一个领域服务，内部会修改 Entity 的数据
        // BankService.transfer(targetMoney, recipientId);
    }
}
