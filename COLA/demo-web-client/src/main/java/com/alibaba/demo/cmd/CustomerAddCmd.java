package com.alibaba.demo.cmd;

import com.alibaba.demo.dto.CustomerDTO;
import lombok.Data;

@Data
public class CustomerAddCmd{
    private CustomerDTO customerDTO;
}
