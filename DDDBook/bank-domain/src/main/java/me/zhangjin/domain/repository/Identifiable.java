package me.zhangjin.domain.repository;

public interface Identifiable<ID extends Identifier> {
    ID getId();
}
