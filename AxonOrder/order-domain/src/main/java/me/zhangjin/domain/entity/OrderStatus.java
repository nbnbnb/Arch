package me.zhangjin.domain.entity;

import lombok.Getter;

@Getter
public enum OrderStatus {

    Submit(1,"已提交"),
    SendOrder(2,"已下单"),
    ConfirmOrder(3,"已确认"),
    Completed(4,"已完成"),
    Canceled(5,"已取消");

    private final Integer code;
    private final String desc;

    private OrderStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
