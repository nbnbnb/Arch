package me.zhangjin.domain.repository;

// 实体类的 Marker 接口
public interface Entity<ID extends Identifier> extends Identifiable<ID> {

}
