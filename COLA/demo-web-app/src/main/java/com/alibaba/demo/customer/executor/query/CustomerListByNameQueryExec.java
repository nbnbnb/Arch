package com.alibaba.demo.customer.executor.query;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.demo.query.CustomerListByNameQuery;
import com.alibaba.demo.dto.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class CustomerListByNameQueryExec {
    public MultiResponse<CustomerDTO> execute(com.alibaba.demo.query.CustomerListByNameQuery cmd) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("Frank");
        customerDTOList.add(customerDTO);
        return MultiResponse.of(customerDTOList);
    }
}
