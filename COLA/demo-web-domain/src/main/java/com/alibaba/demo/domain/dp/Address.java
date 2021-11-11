package com.alibaba.demo.domain.dp;

import javax.validation.ValidationException;

public class Address {
    private final String city;
    private final String area;
    private final String street;

    public Address(String city, String area, String street) {

        if (city == null) {
            throw new ValidationException("city 不能为空");
        }
        if (street == null) {
            throw new ValidationException("street 不能为空");
        }

        this.city = city;
        this.area = area;
        this.street = street;
    }

    public String getAddress() {
        return city + area + street;
    }
}
