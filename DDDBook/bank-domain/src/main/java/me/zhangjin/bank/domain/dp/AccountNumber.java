package me.zhangjin.bank.domain.dp;

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
