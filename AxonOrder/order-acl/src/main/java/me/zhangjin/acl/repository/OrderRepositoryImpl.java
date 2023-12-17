package me.zhangjin.acl.repository;

import com.alibaba.fastjson.JSON;
import me.zhangjin.domain.acl.logger.OrderLogger;
import me.zhangjin.domain.acl.messaging.MessageProducer;
import me.zhangjin.domain.acl.messaging.MessageTopic;
import me.zhangjin.domain.acl.repository.OrderRepository;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.domain.event.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private OrderLogger orderLogger;

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
        messageProducer.send(MessageTopic.ORDER_DOMAIN_EVENT_TOPIC,buildMessage(currentEvent));
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

    private Map<String,Object> buildMessage(DomainEvent domainEvent) {
        Map<String, Object> message = new HashMap<>();
        message.put("orderId", domainEvent.getOrderId());
        message.put("content", JSON.toJSON(domainEvent));
        return message;
    }
}
