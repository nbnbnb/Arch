package com.alibaba.demo.order;

import com.alibaba.demo.domain.order.Order;
import com.alibaba.demo.domain.order.gateway.OrderGateway;
import org.springframework.stereotype.Component;

@Component
public class OrderGatewayImpl implements OrderGateway {

    @Override
    public Order getOrderByUid(String uid) {
        return null;
    }

}
