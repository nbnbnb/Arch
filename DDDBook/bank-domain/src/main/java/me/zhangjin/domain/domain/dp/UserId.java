package me.zhangjin.domain.domain.dp;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserId {

    Long sourceUserId;

    public Long getValue() {
        return sourceUserId;
    }

}
