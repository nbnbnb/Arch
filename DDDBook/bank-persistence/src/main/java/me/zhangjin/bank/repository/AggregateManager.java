package me.zhangjin.bank.repository;

public interface AggregateManager<T, ID> {

    void attach(T aggregate);

    void attach(T aggregate, ID id);

    void detach(T aggregate);

    T find(ID id);

    EntityDiff detectChanges(T aggregate);

    void merge(T aggregate);

    static <T extends Aggregate<ID>, ID extends Identifier> AggregateManager<T, ID> newInstance(Class<?> clazz) {
        return null;
    }
}
