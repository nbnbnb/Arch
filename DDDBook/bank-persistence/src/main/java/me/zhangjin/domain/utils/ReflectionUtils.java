package me.zhangjin.domain.utils;

import me.zhangjin.domain.repository.Aggregate;
import me.zhangjin.domain.repository.Identifier;

public class ReflectionUtils {
    public static <T extends Aggregate<ID>, ID extends Identifier> void writeField(String id, T aggregate, ID id1) {
    }
}
