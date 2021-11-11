package me.zhangjin.bank.types;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserId {
    Long sourceUserId;

    public Long getId() {
        return sourceUserId;
    }
}
