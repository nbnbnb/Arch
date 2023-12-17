package me.zhangjin.application.handler.linea.event;

import com.alibaba.fastjson.JSON;
import me.zhangjin.domain.acl.messaging.MessageProducer;
import me.zhangjin.domain.acl.messaging.MessageTopic;
import me.zhangjin.domain.acl.repository.OrderRepository;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.domain.event.DomainEvent;
import me.zhangjin.domain.event.SubmitOrderEvent;
import me.zhangjin.domain.command.SendVenderCommand;
import net.engio.mbassy.listener.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// 接收到 SubmitOrderEvent 事件后，执行业务逻辑
// 示例
// 1. 发送下单成功短信
// 2. 发送下单成功邮件
// 3. 下供应商订单

// 注意
// 如果执行完逻辑后，需要变更订单状态
// 则需要调用 repository.save 方法，将最新的快照保存到 DB，同时发送 DomainEvent 到 MQ
// 也就是所有涉及到对象状态变更的地方，都需要关联对应的 DomainEvent

// 如果不需要变更订单状态，则直接执行业务逻辑即可
// 例如发送短信/邮件，不需要记录到订单，也不需要发送 DomainEvent
// 下供应商订单，需要更新订单状态，则需要调用 repository.save 方法

@Component
public class SubmitOrderEventHandler {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private MessageProducer messageProducer;

    // 对外的接口调用，尽量用 QMQ 方式解耦，避免同步接口调用
    // 保证 Prcocess 服务的高可用性

    @Handler
    public void sendSMS(SubmitOrderEvent event) {
        messageProducer.send(MessageTopic.ORDER_SEND_SMS,buildMessage(event));
    }

    @Handler
    public void sendEmail(SubmitOrderEvent event) {
        messageProducer.send(MessageTopic.ORDER_SEND_EMAIL,buildMessage(event));
    }

    @Handler
    public void sendVender(SubmitOrderEvent event) {

        // 01 load
        Order order = repository.load(event.getOrderId());

        // 02. verify
        // 业务逻辑检查

        // 03
        // 对接供应商层，可以专门部署对应的微服务
        // 通过 MQ 进行适配，对接底层不同供应商（同步/异步）
        // 保持 Process 这层接口的稳定性
        messageProducer.send(MessageTopic.ORDER_SEND_VENDER,buildMessage(event));

        // 04 变更快照状态（内存）
        SendVenderCommand sendVenderCommand = new SendVenderCommand();
        sendVenderCommand.setOrderId(event.getOrderId());
        order.sendVender(sendVenderCommand);

        // 05. 保存最新快照，并发送 MQ
        repository.save(order);
    }

    private Map<String,Object> buildMessage(DomainEvent domainEvent) {
        Map<String, Object> message = new HashMap<>();
        message.put("orderId", domainEvent.getOrderId());
        message.put("content", JSON.toJSON(domainEvent));
        return message;
    }

}
