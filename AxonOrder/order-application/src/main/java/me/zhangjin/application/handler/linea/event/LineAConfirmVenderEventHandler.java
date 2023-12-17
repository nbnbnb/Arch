package me.zhangjin.application.handler.linea.event;

import me.zhangjin.domain.acl.messaging.MessageProducer;
import me.zhangjin.domain.acl.repository.OrderRepository;
import me.zhangjin.domain.command.common.CommonCompleteOrderCommand;
import me.zhangjin.domain.entity.CompleteType;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.domain.event.linea.LineAConfirmVenderEvent;
import net.engio.mbassy.listener.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class LineAConfirmVenderEventHandler {

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private OrderRepository repository;

    @Handler
    public void sendSMS(LineAConfirmVenderEvent event) {
        Map<String, String> data = new HashMap<>();
        data.put("phone", "13888888888");
        data.put("content", "test test");
        // 发送确认短信
        messageProducer.sendSMS(data);
    }

    @Handler
    public void sendDelayCompleteMQ(LineAConfirmVenderEvent event) {

        Order order = repository.load(event.getOrderId());

        LocalDateTime delayTime = LocalDateTime.now().minusDays(1);

        CommonCompleteOrderCommand autoCompleteCommand = new CommonCompleteOrderCommand();
        autoCompleteCommand.setCompleteType(CompleteType.Auto);
        autoCompleteCommand.setOrderId(order.getOrderId());
        autoCompleteCommand.setProcessType(order.getProcessType());

        // 外部消息需要触发系统状态变更，必须发送 DomainCommand（与 SOA 接口请求一致）
        // 发送延迟消息（DomainCommand）
        messageProducer.sendDomainCommand(autoCompleteCommand, delayTime);
    }


}
