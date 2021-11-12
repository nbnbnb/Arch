package me.zhangjin.bank.domain.dp;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class AccountId {
    Long accountId;

    public Long getValue() {
        return accountId;
    }
}
