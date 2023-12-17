package me.zhangjin.acl.lock;

import me.zhangjin.acl.acl.logger.OrderLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class DLock {

    @Autowired
    private OrderLogger orderLogger;
    private final String key;

    public DLock(String key) {
        this.key = key;
    }

    public Boolean tryLock(int expire, TimeUnit timeUnit) {
        orderLogger.info("success - tryLock: " + key);
        return true;
    }

    public void unlock() {
        orderLogger.info("success - unlock: " + key);
    }

}
