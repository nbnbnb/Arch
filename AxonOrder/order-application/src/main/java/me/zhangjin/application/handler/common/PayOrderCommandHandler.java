package me.zhangjin.application.handler.common;

import me.zhangjin.domain.command.common.CommonPayOrderCommand;
import net.engio.mbassy.listener.Handler;
import org.springframework.stereotype.Component;

@Component
public class PayOrderCommandHandler {

    @Handler
    public void completeOrder(CommonPayOrderCommand command) {

    }

}
