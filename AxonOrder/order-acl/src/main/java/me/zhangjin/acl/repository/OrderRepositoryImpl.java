package me.zhangjin.acl.repository;

import com.alibaba.fastjson.JSON;
import me.zhangjin.domain.acl.logger.OrderLogger;
import me.zhangjin.domain.acl.messaging.MessageProducer;
import me.zhangjin.domain.acl.repository.OrderRepository;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.domain.event.common.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    // 模拟 DB
    private ConcurrentHashMap<Long, Order> orderSnapshotDB = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, List<DomainEvent>> orderEventDB = new ConcurrentHashMap<>();

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

        // 存储最新快照
        saveSnapshot(order);

        // 存储事件
        saveEvent(currentEvent);

        // 发送 Message
        messageProducer.sendDomainEvent(currentEvent);
    }

    private Order loadSnapshot(Long orderId) {
        // TODO 设置不同的路由策略（例如出海场景根据 UDL 路由到不同的库），读取不同的存储
        // MySQL/Redis/OB
        Order order = orderSnapshotDB.get(orderId);

        orderLogger.info("loadSnapshot: %s", JSON.toJSONString(order));

        return order;
    }

    private void saveSnapshot(Order order) {
        // 保存 Snapshot 到 MySQL
        // 保存 Sanpshot 到 Mongo
        orderSnapshotDB.put(order.getOrderId(), order);

        orderLogger.info("saveSnapshot: %s", JSON.toJSONString(order));
    }

    private void saveEvent(DomainEvent domainEvent) {
        // 保存 Event 到 Mongo
        List<DomainEvent> domainEvents = orderEventDB.get(domainEvent.getOrderId());
        if (CollectionUtils.isEmpty(domainEvents)) {
            orderEventDB.put(domainEvent.getOrderId(), new ArrayList<>());
        }
        // 添加到集合中
        orderEventDB.get(domainEvent.getOrderId()).add(domainEvent);

        orderLogger.info("saveEvent: %s", JSON.toJSONString(orderEventDB.get(domainEvent.getOrderId())));
    }

}
