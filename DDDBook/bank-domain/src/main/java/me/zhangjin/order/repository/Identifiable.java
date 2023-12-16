package me.zhangjin.order.repository;

public interface Identifiable<ID extends Identifier> {
    ID getId();
}
