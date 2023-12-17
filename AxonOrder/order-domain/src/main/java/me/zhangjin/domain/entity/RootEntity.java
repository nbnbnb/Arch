package me.zhangjin.domain.entity;


import me.zhangjin.domain.event.common.DomainEvent;

import java.lang.reflect.Method;
import java.util.*;

public abstract class RootEntity {

    private static final String MUTATOR_METHOD_NAME = "when";

    private static Map<String, Method> mutatorMethods = new HashMap<>();

    private DomainEvent currentEvent;

    public DomainEvent getCurrentEvent() {
        return currentEvent;
    }

    // 每次 apply 时会自动 +1
    private long version;

    public long getVersion() {
        return this.version;
    }

    protected void apply(DomainEvent domainEvent) {

        // 设置 Order 的版本
        // 自动加 1
        this.version = this.version + 1;

        // 设置 Event 的版本，与 Order 保持一致
        // 便于后续排查问题
        domainEvent.setEventVersion(getVersion());

        // 添加到临时集合中
        // repository.save 时使用
        this.currentEvent = domainEvent;

        // 执行 when 方法（反射找到对应的签名方法）
        this.mutateWhen(domainEvent);
    }

    private void mutateWhen(DomainEvent domainEvent) {

        Class<? extends RootEntity> rootType = this.getClass();
        Class<? extends DomainEvent> eventType = domainEvent.getClass();

        String key = rootType.getName() + ":" + eventType.getName();
        Method mutatorMethod = mutatorMethods.get(key);

        if (mutatorMethod == null) {
            mutatorMethod = this.cacheMutatorMethodFor(key, rootType, eventType);
        }

        try {
            mutatorMethod.invoke(this, domainEvent);
        } catch (Exception e) {
            throw new RuntimeException("Method " + MUTATOR_METHOD_NAME + "(" + eventType.getSimpleName() + ") failed. See cause: " + e.getMessage(), e);
        }

    }

    private Method cacheMutatorMethodFor(String aKey, Class<? extends RootEntity> rootType, Class<? extends DomainEvent> eventType) {

        // 使用全局锁，运行时缓存
        // 也可以在类初始化时缓存
        synchronized (mutatorMethods) {
            try {
                // 只会执行一个，注意不要定义重复的方法
                Method method = rootType.getDeclaredMethod(MUTATOR_METHOD_NAME, eventType);
                // private 可执行
                method.setAccessible(true);
                mutatorMethods.put(aKey, method);
                return method;
            } catch (Exception e) {
                throw new IllegalArgumentException("I do not understand " + MUTATOR_METHOD_NAME + "(" + eventType.getSimpleName() + ") because: " + e.getClass().getSimpleName() + ">>>" + e.getMessage(), e);
            }
        }
    }


}
