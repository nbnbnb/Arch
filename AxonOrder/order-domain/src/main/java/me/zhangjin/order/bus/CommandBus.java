package me.zhangjin.order.bus;

import me.zhangjin.order.acl.lock.DLock;
import me.zhangjin.order.acl.lock.DistLock;
import me.zhangjin.order.acl.lock.Locker;
import me.zhangjin.order.command.AbstractCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CommandBus {

    @Autowired
    private CommonMessageBus bus;

    @Autowired
    private Locker locker;

    public void send(AbstractCommand command) {
        String lockKey = command.getOrderId().toString();
        locker.run(lockKey, 10, command, cmd -> bus.publish(cmd));
    }

    public <T> T sendWithResult(AbstractCommand command) {
        String lockKey = command.getOrderId().toString();
        return locker.runWithResult(lockKey, 10, command, cmd -> (T) bus.publishWithResult(cmd).getMessage());
    }

}

