package me.zhangjin.domain.repository;

// AggregateManager 实现
// 主要是通过 ThreadLocal 避免多线程公用同一个 Entity 的情况
public class ThreadLocalAggregateManager<T extends Aggregate<ID>, ID extends Identifier> implements AggregateManager<T, ID> {

    private ThreadLocal<DbContext<T, ID>> context;
    private Class<? extends T> targetClass;

    public ThreadLocalAggregateManager(Class<? extends T> targetClass) {
        this.targetClass = targetClass;
        this.context = ThreadLocal.withInitial(() -> new DbContext<>(targetClass));
    }

    @Override
    public void attach(T aggregate) {
        context.get().attach(aggregate);
    }

    @Override
    public void attach(T aggregate, ID id) {
        context.get().setId(aggregate, id);
        context.get().attach(aggregate);
    }

    @Override
    public void detach(T aggregate) {
        context.get().detach(aggregate);
    }

    @Override
    public T find(ID id) {
        return context.get().find(id);
    }

    @Override
    public EntityDiff detectChanges(T aggregate) {
        return context.get().detectChanges(aggregate);
    }

    @Override
    public void merge(T aggregate) {
        context.get().merge(aggregate);
    }
}
