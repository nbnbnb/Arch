package me.zhangjin.acl.application;

import me.zhangjin.acl.application.OrderApp;
import me.zhangjin.acl.bus.CommandBus;
import me.zhangjin.acl.command.SubmitOrderCommand;
import me.zhangjin.acl.dto.SubmitOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderAppImpl implements OrderApp {

    @Autowired
    private CommandBus bus;

    @Override
    public SubmitOrderDTO submitOrder(SubmitOrderCommand submitOrderCommand) {

        bus.send(submitOrderCommand);

        return null;
    }
}
