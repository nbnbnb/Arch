package me.zhangjin.bank.repository;

// 聚合根的Marker接口
public interface Aggregate<ID extends Identifier> extends Entity<ID> {

}
