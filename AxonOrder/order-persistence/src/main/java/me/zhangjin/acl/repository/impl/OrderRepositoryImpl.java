package me.zhangjin.acl.repository.impl;

import me.zhangjin.acl.acl.messaging.EventMessageProducer;
import me.zhangjin.acl.acl.repository.OrderRepository;
import me.zhangjin.acl.domain.entity.Order;
import me.zhangjin.acl.domain.entity.event.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private EventMessageProducer eventMessageProducer;

    @Override
    public Order load(Long orderId) {
        return loadSnapshot(orderId);
    }

    @Override
    public void save(Order order) {

        // 获取 apply 方法存储的 Event
        DomainEvent currentEvent = order.getCurrentEvent();

        // 存储事件
        saveEvent(currentEvent);

        // 存储最新快照
        saveSnapshot(order);

        // 发送 Message
        eventMessageProducer.send(currentEvent);
    }

    private Order loadSnapshot(Long orderId) {
        // TODO 设置不同的路由策略（例如出海场景根据 UDL 路由到不同的库），读取不同的存储
        // MySQL/Redis/OB
        return null;
    }

    private void saveSnapshot(Order order) {
        // 保存 Snapshot 到 MySQL

        // 保存 Sanpshot 到 Mongo
    }

    private void saveEvent(DomainEvent domainEvent) {
        // 保存 Event 到 Mongo
    }
}
