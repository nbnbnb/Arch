package me.zhangjin.bank.types;

import lombok.AllArgsConstructor;
import lombok.Value;


import java.util.Date;

@Value
@AllArgsConstructor
public class AuditMessage {

    UserId userId;
    AccountNumber source;
    AccountNumber target;
    Money money;
    Date date;

    public String serialize() {
        return userId + "," + source + "," + target + "," + money + "," + date;
    }

    public static AuditMessage deserialize(String value) {
        // todo
        return null;
    }

}
