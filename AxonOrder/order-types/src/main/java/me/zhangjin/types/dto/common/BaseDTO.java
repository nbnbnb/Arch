package me.zhangjin.types.dto.common;

import lombok.Getter;
import lombok.Setter;

public abstract class BaseDTO {

    @Getter
    @Setter
    private Boolean success;

    @Getter
    @Setter
    private String message;

}
