package me.zhangjin.order.message;

import lombok.AllArgsConstructor;
import lombok.Value;


import java.math.BigDecimal;
import java.util.Date;

@Value
@AllArgsConstructor
public class AuditMessage {

    Long userId;
    String source;
    String target;
    BigDecimal money;
    Date date;

    public String serialize() {
        return userId + "," + source + "," + target + "," + money + "," + date;
    }

    // 因为中间件通常需要有通用型，中间件的接口通常是 String 或 Byte[] 类型的
    public static AuditMessage deserialize(String value) {
        // todo
        return null;
    }

}
