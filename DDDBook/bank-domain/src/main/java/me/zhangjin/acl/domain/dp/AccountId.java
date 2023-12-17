package me.zhangjin.acl.domain.dp;

import lombok.AllArgsConstructor;
import lombok.Value;
import me.zhangjin.acl.repository.Identifier;

@Value
@AllArgsConstructor
public class AccountId implements Identifier {

    Long accountId;

    public Long getValue() {
        return accountId;
    }

}
