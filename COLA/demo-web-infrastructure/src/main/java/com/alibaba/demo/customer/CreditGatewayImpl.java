package com.alibaba.demo.customer;

import com.alibaba.demo.domain.customer.Credit;
import com.alibaba.demo.domain.customer.gateway.CreditGateway;
import org.springframework.stereotype.Component;


/**
 * 实现领域层的接口
 */
@Component
public class CreditGatewayImpl implements CreditGateway {

    public Credit getCredit(String customerId) {
        return null;
    }

}
