package me.zhangjin.acl.domain.dp;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@AllArgsConstructor
public class AccountId implements Serializable {

    Long accountId;

    public Long getValue() {
        return accountId;
    }

}
