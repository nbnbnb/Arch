package me.zhangjin.domain.domain.dp;

import lombok.Value;


@Value
public class NewCurrency {
    String currency;

    public String getValue() {
        return currency;
    }
}
