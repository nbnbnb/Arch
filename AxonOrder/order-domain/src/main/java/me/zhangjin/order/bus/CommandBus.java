package me.zhangjin.order.bus;

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

    public void send(AbstractCommand command) {
        bus.publish(command);
    }

    public <T> T sendWithResult(AbstractCommand command) {
        return (T) bus.publishWithResult(command).getMessage();
    }

}

