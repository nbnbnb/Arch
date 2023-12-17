package me.zhangjin.acl.lock;

import me.zhangjin.domain.acl.logger.OrderLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

public class DLock {

    private OrderLogger orderLogger;
    private final String key;

    public DLock(String key, OrderLogger orderLogger) {
        this.key = key;
        this.orderLogger = orderLogger;
    }

    public Boolean tryLock(int expire, TimeUnit timeUnit) {
        orderLogger.info("success - tryLock: " + key);
        return true;
    }

    public void unlock() {
        orderLogger.info("success - unlock: " + key);
    }

}
