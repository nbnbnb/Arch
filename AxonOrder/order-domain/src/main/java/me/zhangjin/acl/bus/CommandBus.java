package me.zhangjin.acl.bus;

import me.zhangjin.acl.acl.lock.Locker;
import me.zhangjin.acl.command.AbstractCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

