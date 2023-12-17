package me.zhangjin.acl.application.impl;

import me.zhangjin.acl.application.OrderApp;
import me.zhangjin.acl.bus.CommandBus;
import me.zhangjin.acl.command.SubmitOrderCommand;
import me.zhangjin.acl.domain.service.LossFeeService;
import me.zhangjin.acl.dto.SubmitOrderDTO;
import me.zhangjin.acl.acl.external.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderAppImpl implements OrderApp {

    @Autowired
    private CommandBus bus;

    // ACL - 外部服务
    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private LossFeeService lossFeeService;

    @Override
    public SubmitOrderDTO submitOrder(SubmitOrderCommand submitOrderCommand) {

        bus.send(submitOrderCommand);

        return null;
    }
}
