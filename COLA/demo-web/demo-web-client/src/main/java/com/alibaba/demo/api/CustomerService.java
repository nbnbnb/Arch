package com.alibaba.demo.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.demo.cmd.CustomerAddCmd;
import com.alibaba.demo.query.CustomerListByNameQuery;
import com.alibaba.demo.dto.CustomerDTO;

/**
 * 服务对外透出的 API
 */
public interface CustomerService {

    Response addCustomer(CustomerAddCmd customerAddCmd);

    MultiResponse<CustomerDTO> listByName(CustomerListByNameQuery customerListByNameQry);
}
