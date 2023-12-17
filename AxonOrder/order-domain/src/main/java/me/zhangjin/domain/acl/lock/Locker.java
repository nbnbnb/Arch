package me.zhangjin.domain.acl.lock;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Locker {

    <T> void run(String lockKey, Integer expire, T obj, Consumer<T> action);

    <T, R> R runWithResult(String lockKey, Integer expire, T obj, Function<T, R> func);

}
