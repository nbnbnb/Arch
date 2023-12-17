package me.zhangjin.application.handler.linea.event;

import me.zhangjin.domain.acl.messaging.MessageProducer;
import me.zhangjin.domain.acl.repository.OrderRepository;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.linea.SubmitOrderEvent;
import me.zhangjin.domain.command.linea.SendVenderCommand;
import me.zhangjin.types.exception.BizExceptioin;
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
        Map<String, String> data = new HashMap<>();
        data.put("phone", "13888888888");
        data.put("content", "test test");
        // 发送确认短信
        messageProducer.sendSMS(data);
    }

    @Handler
    public void sendEmail(SubmitOrderEvent event) {
        Map<String, String> data = new HashMap<>();
        data.put("email", "abc@abc.com");
        data.put("content", "test test test");
        // 发送确认短信
        messageProducer.sendEmail(data);
    }

    @Handler
    public void sendVender(SubmitOrderEvent event) {

        // 01 load
        Order order = repository.load(event.getOrderId());

        // 02. verify
        // 业务逻辑检查
        if (order.getOrderStatus() != OrderStatus.Submit) {
            throw new BizExceptioin("1003", "sendVender status check fail");
        }

        Map<String, String> data = new HashMap<>();
        data.put("orderid", event.getOrderId().toString());
        // 03
        // 对接供应商层，可以专门部署对应的微服务
        // 通过 MQ 进行适配，对接底层不同供应商（同步/异步）
        // 保持 Process 这层接口的稳定性
        messageProducer.sendVendor(data);

        // 04 变更快照状态（内存）
        SendVenderCommand sendVenderCommand = new SendVenderCommand(event.getOrderId(), event.getProcessType());
        order.sendVender(sendVenderCommand);

        // 05. 保存最新快照，并发送 MQ
        // 发送 SendVenderEvent 到 MQ
        repository.save(order);
    }


}
