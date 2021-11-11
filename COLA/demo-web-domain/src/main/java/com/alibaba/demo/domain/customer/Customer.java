package com.alibaba.demo.domain.customer;

import com.alibaba.cola.domain.Entity;
import com.alibaba.cola.exception.BizException;
import lombok.Data;

//Domain Entity can choose to extends the domain model which is used for DTO
@Data
@Entity
public class Customer {

    private String customerId;
    private String memberId;
    private String globalId;
    private long registeredCapital;
    private String companyName;
    private SourceType sourceType;
    private CompanyType companyType;

    public Customer() {
    }

    public boolean isBigCompany() {
        // 注册资金大于 1000 万的是大企业
        return registeredCapital > 10000000;
    }

    public boolean isSME() {
        //注册资金大于 10 万小于 100 万的为中小企业
        return registeredCapital > 10000 && registeredCapital < 1000000;
    }

    public void checkConfilict() {
        // Per different biz, the check policy could be different, if so, use ExtensionPoint
        if ("ConflictCompanyName".equals(this.companyName)) {
            throw new BizException(this.companyName + " has already existed, you can not add it");
        }
    }
}
