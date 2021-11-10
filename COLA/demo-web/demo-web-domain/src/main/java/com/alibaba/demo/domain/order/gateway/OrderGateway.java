package com.alibaba.demo.domain.order.gateway;

import com.alibaba.demo.domain.order.Order;

/**
 * 调用 DB
 * 基础服务层实现
 */
public interface OrderGateway {
    Order getOrderByUid(String uid);
}
