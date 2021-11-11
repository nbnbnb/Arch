package me.zhangjin.bank.types;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Currency {
    String currency;

    public String getValue() {
        return currency;
    }
}
