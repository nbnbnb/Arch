package me.zhangjin.acl.lock;

public class DistLock {
    public DLock getLock(String key){
        return new DLock(key);
    }
}
