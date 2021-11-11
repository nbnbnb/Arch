package me.zhangjin.bank.types;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountNumber {
    String accountNumber;

    public String getValue() {
        return accountNumber;
    }
}
