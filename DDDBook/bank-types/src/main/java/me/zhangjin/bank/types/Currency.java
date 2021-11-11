package me.zhangjin.bank.types;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Currency {
    String currency;

    public String getValue() {
        return currency;
    }
}
