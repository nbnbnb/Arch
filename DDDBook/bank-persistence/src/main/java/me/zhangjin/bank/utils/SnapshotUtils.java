package me.zhangjin.bank.utils;

import me.zhangjin.bank.repository.Aggregate;
import me.zhangjin.bank.repository.Identifier;

public class SnapshotUtils {
    public static <T extends Aggregate<ID>, ID extends Identifier> T snapshot(T aggregate) {
        return null;
    }
}
