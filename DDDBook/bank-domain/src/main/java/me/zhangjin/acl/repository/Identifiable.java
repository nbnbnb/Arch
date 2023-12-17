package me.zhangjin.acl.repository;

public interface Identifiable<ID extends Identifier> {
    ID getId();
}
