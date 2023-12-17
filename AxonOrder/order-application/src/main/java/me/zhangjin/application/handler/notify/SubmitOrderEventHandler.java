package me.zhangjin.application.handler.notify;

import me.zhangjin.domain.acl.repository.OrderRepository;
import me.zhangjin.domain.domain.event.SubmitOrderEvent;
import net.engio.mbassy.listener.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    // 对外的接口调用，尽量用 QMQ 方式解耦，避免同步接口调用
    // 保证 Prcocess 服务的高可用性

    @Handler
    public void sendSMS(SubmitOrderEvent event) {

    }

    @Handler
    public void sendEmail(SubmitOrderEvent event) {

    }

    @Handler
    public void sendVendorOrder(SubmitOrderEvent event) {
        // 对接供应商层，可以专门部署对应的微服务，对接底层不同供应商
        // 保持 Process 这层接口的稳定性

    }

}
