package me.zhangjin.order.utils;

import me.zhangjin.order.repository.Aggregate;
import me.zhangjin.order.repository.EntityDiff;
import me.zhangjin.order.repository.Identifier;

public class DiffUtils {
    public static <T extends Aggregate<ID>, ID extends Identifier> EntityDiff diff(T snapshot, T aggregate) {
        return null;
    }
}
