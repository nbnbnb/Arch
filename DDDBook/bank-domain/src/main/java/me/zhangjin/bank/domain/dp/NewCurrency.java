package me.zhangjin.bank.domain.dp;

import lombok.AllArgsConstructor;
import lombok.Value;


@Value
public class NewCurrency {
    String currency;

    public String getValue() {
        return currency;
    }
}
