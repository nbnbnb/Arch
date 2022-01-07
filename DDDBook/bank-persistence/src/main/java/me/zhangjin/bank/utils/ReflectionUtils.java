package me.zhangjin.bank.utils;

import me.zhangjin.bank.repository.Aggregate;
import me.zhangjin.bank.repository.Identifier;

public class ReflectionUtils {
    public static <T extends Aggregate<ID>, ID extends Identifier> void writeField(String id, T aggregate, ID id1) {
    }
}
