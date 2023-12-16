package me.zhangjin.order.utils;

import me.zhangjin.order.repository.Aggregate;
import me.zhangjin.order.repository.Identifier;

public class ReflectionUtils {
    public static <T extends Aggregate<ID>, ID extends Identifier> void writeField(String id, T aggregate, ID id1) {
    }
}
