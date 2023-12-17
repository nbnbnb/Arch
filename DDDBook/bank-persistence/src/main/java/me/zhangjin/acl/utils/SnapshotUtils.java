package me.zhangjin.acl.utils;

import me.zhangjin.acl.repository.Aggregate;
import me.zhangjin.acl.repository.Identifier;

public class SnapshotUtils {
    public static <T extends Aggregate<ID>, ID extends Identifier> T snapshot(T aggregate) {
        return null;
    }
}
