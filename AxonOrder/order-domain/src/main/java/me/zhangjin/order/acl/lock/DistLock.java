package me.zhangjin.order.acl.lock;

public interface DistLock {
    DLock getLock(String key);
}
