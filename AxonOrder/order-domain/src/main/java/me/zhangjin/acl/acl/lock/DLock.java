package me.zhangjin.acl.acl.lock;

import java.util.concurrent.TimeUnit;

public interface DLock {
    Boolean tryLock(int expire, TimeUnit timeUnit);

    void unlock();
}
