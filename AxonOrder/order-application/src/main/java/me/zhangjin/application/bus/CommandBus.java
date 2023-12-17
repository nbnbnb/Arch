package me.zhangjin.application.bus;

import me.zhangjin.domain.acl.lock.Locker;
import me.zhangjin.domain.command.DomainCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandBus {

    @Autowired
    private CommonMessageBus bus;

    @Autowired
    private Locker locker;

    public void send(DomainCommand command) {
        String lockKey = command.getOrderId().toString();
        locker.run(lockKey, 10, command, cmd -> bus.publish(cmd));
    }

    public <T> T sendWithResult(DomainCommand command) {
        String lockKey = command.getOrderId().toString();
        return locker.runWithResult(lockKey, 10, command, cmd -> (T) bus.publishWithResult(cmd).getMessage());
    }

}

