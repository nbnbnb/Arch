package com.alibaba.demo.domain.user;

import lombok.Data;

@Data
public class User {
    private Long userId;
    private String name;
    private String phone;
    private String address;
    private Long repId;
}