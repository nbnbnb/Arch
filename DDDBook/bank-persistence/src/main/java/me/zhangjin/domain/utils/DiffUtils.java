package me.zhangjin.domain.utils;

import me.zhangjin.domain.repository.Aggregate;
import me.zhangjin.domain.repository.EntityDiff;
import me.zhangjin.domain.repository.Identifier;

public class DiffUtils {
    public static <T extends Aggregate<ID>, ID extends Identifier> EntityDiff diff(T snapshot, T aggregate) {
        return null;
    }
}
