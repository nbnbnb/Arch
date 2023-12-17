package me.zhangjin.application.handler.common;

import me.zhangjin.domain.command.common.PayOrderCommand;
import net.engio.mbassy.listener.Handler;
import org.springframework.stereotype.Component;

@Component
public class PayOrderCommandHandler {

    @Handler
    public void completeOrder(PayOrderCommand command) {

    }

}
