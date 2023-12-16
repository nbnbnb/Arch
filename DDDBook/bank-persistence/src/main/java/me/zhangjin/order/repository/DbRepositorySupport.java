package me.zhangjin.order.repository;

import lombok.AccessLevel;
import lombok.Getter;

import javax.validation.constraints.NotNull;

// 这个类是一个通用的支撑类，为了减少开发者的重复劳动
// 在用的时候需要继承这个类
public abstract class DbRepositorySupport<T extends Aggregate<ID>, ID extends Identifier> implements Repository<T, ID> {

    @Getter
    private final Class<T> targetClass;

    // 让 AggregateManager 去维护 Snapshot
    @Getter(AccessLevel.PROTECTED)
    private final AggregateManager<T, ID> aggregateManager;

    protected DbRepositorySupport(Class<T> targetClass) {
        this.targetClass = targetClass;
        this.aggregateManager = AggregateManager.newInstance(targetClass);
    }

    // region 这几个方法是继承的子类应该去实现的（实际 DAO 访问）

    protected abstract void onInsert(T aggregate);

    protected abstract T onSelect(ID id);

    protected abstract void onUpdate(T aggregate, EntityDiff diff);

    protected abstract void onDelete(T aggregate);

    // endregion

    // region Repository 接口方法

    /**
     * Attach 的操作就是让 Aggregate 可以被追踪
     */
    @Override
    public void attach(@NotNull T aggregate) {
        this.aggregateManager.attach(aggregate);
    }

    /**
     * Detach 的操作就是让 Aggregate 停止追踪
     */
    @Override
    public void detach(@NotNull T aggregate) {
        this.aggregateManager.detach(aggregate);
    }

    @Override
    public T find(@NotNull ID id) {
        // 调用重写的方法（实际 DAO 访问）
        T aggregate = this.onSelect(id);

        if (aggregate != null) {
            // 这里的就是让查询出来的对象能够被追踪
            // 如果自己实现了一个定制查询接口，要记得单独调用 attach
            this.attach(aggregate);
        }
        return aggregate;
    }

    @Override
    public void remove(@NotNull T aggregate) {
        // 调用重写的方法（实际 DAO 访问）
        this.onDelete(aggregate);

        // 删除停止追踪
        this.detach(aggregate);
    }

    @Override
    public T save(@NotNull T aggregate) {
        // 如果没有 ID，直接插入
        if (aggregate.getId() == null) {

            // 调用重写的方法（实际 DAO 访问）
            this.onInsert(aggregate);

            this.attach(aggregate);
        }

        // 做 Diff
        EntityDiff diff = aggregateManager.detectChanges(aggregate);

        if (!diff.isEmpty()) {

            // 调用重写的方法（实际 DAO 访问）
            this.onUpdate(aggregate, diff);

            // 最终将 DB 带来的变化更新回 AggregateManager
            aggregateManager.merge(aggregate);
        }

        return aggregate;
    }

    // endregion

}
