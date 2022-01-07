package me.zhangjin.bank.utils;

import me.zhangjin.bank.repository.Aggregate;
import me.zhangjin.bank.repository.EntityDiff;
import me.zhangjin.bank.repository.Identifier;

public class DiffUtils {
    public static <T extends Aggregate<ID>, ID extends Identifier> EntityDiff diff(T snapshot, T aggregate) {
        return null;
    }
}
