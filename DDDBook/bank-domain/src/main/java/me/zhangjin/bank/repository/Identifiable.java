package me.zhangjin.bank.repository;

public interface Identifiable<ID extends Identifier> {
    ID getId();
}
