package me.zhangjin.order.repository;

// 聚合根的 Marker 接口
public interface Aggregate<ID extends Identifier> extends Entity<ID> {

}
