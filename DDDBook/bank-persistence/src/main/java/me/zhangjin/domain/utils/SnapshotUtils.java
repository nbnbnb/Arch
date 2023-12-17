package me.zhangjin.domain.utils;

import me.zhangjin.domain.repository.Aggregate;
import me.zhangjin.domain.repository.Identifier;

public class SnapshotUtils {
    public static <T extends Aggregate<ID>, ID extends Identifier> T snapshot(T aggregate) {
        return null;
    }
}
