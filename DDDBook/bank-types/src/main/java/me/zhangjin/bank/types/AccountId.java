package me.zhangjin.bank.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Value
@AllArgsConstructor
public class AccountId {
    Long accountId;

    public Long getValue() {
        return accountId;
    }
}
