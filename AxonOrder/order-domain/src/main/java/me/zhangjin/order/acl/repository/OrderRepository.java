package me.zhangjin.order.acl.repository;

import me.zhangjin.order.domain.entity.Order;

public interface OrderRepository {
    Order load(Long orderId);

    void save(Order order);
}
