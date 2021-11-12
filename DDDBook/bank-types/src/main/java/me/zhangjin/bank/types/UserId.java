package me.zhangjin.bank.types;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserId {

    Long sourceUserId;

    public Long getId() {
        return sourceUserId;
    }

}
