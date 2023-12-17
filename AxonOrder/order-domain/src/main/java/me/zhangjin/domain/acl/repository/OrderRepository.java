package me.zhangjin.domain.acl.repository;


import me.zhangjin.domain.entity.Order;

public interface OrderRepository {
    Order load(Long orderId);

    void save(Order order);
}
