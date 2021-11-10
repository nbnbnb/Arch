package com.alibaba.demo.query;

import com.alibaba.cola.dto.Query;
import lombok.Data;

@Data
public class CustomerListByNameQuery extends Query{
   private String name;
}
