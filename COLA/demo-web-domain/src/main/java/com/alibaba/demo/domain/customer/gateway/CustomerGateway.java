package com.alibaba.demo.domain.customer.gateway;

import com.alibaba.demo.domain.customer.Customer;

/**
 * 调用 DB
 * <p>
 * 基础服务层实现
 */
public interface CustomerGateway {
    Customer getById(String customerId);
}
