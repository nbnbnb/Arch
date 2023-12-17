package me.zhangjin.acl.utils;

import me.zhangjin.acl.repository.Aggregate;
import me.zhangjin.acl.repository.Identifier;

public class ReflectionUtils {
    public static <T extends Aggregate<ID>, ID extends Identifier> void writeField(String id, T aggregate, ID id1) {
    }
}
