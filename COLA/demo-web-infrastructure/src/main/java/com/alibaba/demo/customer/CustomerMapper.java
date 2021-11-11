package com.alibaba.demo.customer;

import org.apache.ibatis.annotations.Mapper;

/**
 * MyBatis 映射
 * <p>
 * 可用 JPA 替换
 */
@Mapper
public interface CustomerMapper {

    CustomerDO getById(String customerId);

}
