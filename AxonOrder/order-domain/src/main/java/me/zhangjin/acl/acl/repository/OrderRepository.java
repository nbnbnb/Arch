package me.zhangjin.acl.acl.repository;

import me.zhangjin.acl.domain.entity.Order;

public interface OrderRepository {
    Order load(Long orderId);

    void save(Order order);
}
