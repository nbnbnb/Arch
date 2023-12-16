package me.zhangjin.order.application.impl;

import me.zhangjin.order.application.OrderService;
import me.zhangjin.order.bus.CommandBus;
import me.zhangjin.order.command.SubmitOrderCommand;
import me.zhangjin.order.domain.service.LossFeeService;
import me.zhangjin.order.dto.SubmitOrderDTO;
import me.zhangjin.order.acl.external.ExchangeRateService;
import me.zhangjin.order.acl.messaging.AuditMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CommandBus bus;

    // ACL - 抽象中间件
    @Autowired
    private AuditMessageProducer auditMessageProducer;

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
