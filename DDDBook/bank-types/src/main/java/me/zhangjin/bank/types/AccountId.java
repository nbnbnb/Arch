package me.zhangjin.bank.types;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountId {
    Long accountId;

    public Long getValue() {
        return accountId;
    }
}
