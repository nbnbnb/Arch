package com.alibaba.demo.domain.dp;

import lombok.Data;

import javax.validation.ValidationException;

@Data
public class Name {
    private final String firstName;

    private final String lastName;

    public Name(String firstName, String lastName) {
        if (firstName == null) {
            throw new ValidationException("firstName 不能为空");
        }
        if (lastName == null) {
            throw new ValidationException("lastName 不能为空");
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
