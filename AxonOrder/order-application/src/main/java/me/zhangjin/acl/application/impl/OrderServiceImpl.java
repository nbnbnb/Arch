package me.zhangjin.acl.application.impl;

import me.zhangjin.acl.application.OrderService;
import me.zhangjin.acl.bus.CommandBus;
import me.zhangjin.acl.command.SubmitOrderCommand;
import me.zhangjin.acl.domain.service.LossFeeService;
import me.zhangjin.acl.dto.SubmitOrderDTO;
import me.zhangjin.acl.acl.external.ExchangeRateService;
import me.zhangjin.acl.acl.messaging.AuditMessageProducer;
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
