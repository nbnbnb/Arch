package me.zhangjin.domain.entity;

import lombok.Getter;

@Getter
public enum CompleteType {

    Auto(1, "自动完成"),
    Manual(2, "手动完成");

    private final Integer code;
    private final String desc;

    private CompleteType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
