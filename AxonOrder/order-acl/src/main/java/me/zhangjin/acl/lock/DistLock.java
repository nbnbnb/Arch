package me.zhangjin.acl.lock;

import me.zhangjin.domain.acl.logger.OrderLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistLock {

    @Autowired
    private OrderLogger orderLogger;

    public DLock getLock(String key) {
        return new DLock(key, orderLogger);
    }
}
