package com.alibaba.demo.customer;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.demo.api.CustomerService;
import com.alibaba.demo.dto.CustomerDTO;
import org.springframework.stereotype.Service;

import com.alibaba.demo.customer.executor.cmd.CustomerAddCmdExec;
import com.alibaba.demo.customer.executor.query.CustomerListByNameQueryExec;

import javax.annotation.Resource;


@Service
@CatchAndLog
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerAddCmdExec customerAddCmdExec;

    @Resource
    private CustomerListByNameQueryExec customerListByNameQueryExec;

    public Response addCustomer(com.alibaba.demo.cmd.CustomerAddCmd customerAddCmd) {
        return customerAddCmdExec.execute(customerAddCmd);
    }

    @Override
    public MultiResponse<CustomerDTO> listByName(com.alibaba.demo.query.CustomerListByNameQuery customerListByNameQry) {
        return customerListByNameQueryExec.execute(customerListByNameQry);
    }

}