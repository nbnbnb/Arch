package me.zhangjin.domain.domain.dp;

import lombok.AllArgsConstructor;
import lombok.Value;
import me.zhangjin.domain.repository.Identifier;

@Value
@AllArgsConstructor
public class AccountId implements Identifier {

    Long accountId;

    public Long getValue() {
        return accountId;
    }

}
