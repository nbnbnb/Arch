package me.zhangjin.bank.domain.types;

import lombok.AllArgsConstructor;
import lombok.Value;
import me.zhangjin.bank.domain.entity.Account;
import me.zhangjin.bank.types.AccountNumber;
import me.zhangjin.bank.types.Money;
import me.zhangjin.bank.types.UserId;

import java.util.Date;

@Value
@AllArgsConstructor
public class AuditMessage {

    UserId userId;
    Account source;
    Account target;
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
