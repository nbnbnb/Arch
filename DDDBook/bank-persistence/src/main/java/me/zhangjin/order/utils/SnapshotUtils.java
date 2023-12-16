package me.zhangjin.order.utils;

import me.zhangjin.order.repository.Aggregate;
import me.zhangjin.order.repository.Identifier;

public class SnapshotUtils {
    public static <T extends Aggregate<ID>, ID extends Identifier> T snapshot(T aggregate) {
        return null;
    }
}
