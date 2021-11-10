package com.alibaba.demo.domain.customer.gateway;

import com.alibaba.demo.domain.customer.Credit;


/**
 * 调用外部 WebService
 * <p>
 * 基础服务层实现（防腐层）
 */
public interface CreditGateway {
    Credit getCredit(String customerId);
}
