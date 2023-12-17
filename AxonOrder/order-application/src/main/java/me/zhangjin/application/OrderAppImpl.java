package me.zhangjin.application;

import me.zhangjin.domain.bus.CommandBus;
import me.zhangjin.types.command.SubmitOrderCommand;
import me.zhangjin.types.dto.SubmitOrderDTO;
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