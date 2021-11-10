package com.alibaba.demo.customer;

import com.alibaba.demo.domain.customer.Customer;
import com.alibaba.demo.domain.customer.gateway.CustomerGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 实现领域层的接口
 */
@Component
public class CustomerGatewayImpl implements CustomerGateway {

    @Autowired
    private CustomerMapper customerMapper;

    public Customer getById(String customerId) {
        CustomerDO customerDO = customerMapper.getById(customerId);
        //Convert to Customer
        return null;
    }

}
