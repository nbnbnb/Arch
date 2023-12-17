package me.zhangjin.application.handler.common.command;

import me.zhangjin.domain.command.common.CommonPayOrderCommand;
import net.engio.mbassy.listener.Handler;
import org.springframework.stereotype.Component;

@Component
public class CommonPayOrderCommandHandler {

    @Handler
    public void completeOrder(CommonPayOrderCommand command) {

    }

}
