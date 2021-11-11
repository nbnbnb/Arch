package com.alibaba.demo.web;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.demo.api.CustomerService;
import com.alibaba.demo.cmd.CustomerAddCmd;
import com.alibaba.demo.query.CustomerListByNameQuery;
import com.alibaba.demo.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 处理页面请求的 Controller
 */
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/helloworld")
    public String helloWorld() {
        return "Hello, welcome to COLA world!";
    }

    @GetMapping(value = "/customer")
    public MultiResponse<CustomerDTO> listCustomerByName(@RequestParam(required = false) String name) {
        CustomerListByNameQuery customerListByNameQry = new CustomerListByNameQuery();
        customerListByNameQry.setName(name);
        return customerService.listByName(customerListByNameQry);
    }

    @PostMapping(value = "/customer")
    public Response addCustomer(@RequestBody CustomerAddCmd customerAddCmd) {
        return customerService.addCustomer(customerAddCmd);
    }
}
