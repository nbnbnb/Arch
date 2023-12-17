package me.zhangjin.acl.utils;

import me.zhangjin.acl.repository.Aggregate;
import me.zhangjin.acl.repository.EntityDiff;
import me.zhangjin.acl.repository.Identifier;

public class DiffUtils {
    public static <T extends Aggregate<ID>, ID extends Identifier> EntityDiff diff(T snapshot, T aggregate) {
        return null;
    }
}
