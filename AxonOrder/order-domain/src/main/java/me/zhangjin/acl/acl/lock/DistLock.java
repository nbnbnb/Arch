package me.zhangjin.acl.acl.lock;

public interface DistLock {
    DLock getLock(String key);
}
