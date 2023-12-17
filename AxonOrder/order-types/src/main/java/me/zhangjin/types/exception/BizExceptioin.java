package me.zhangjin.types.exception;

import lombok.Getter;

// 业务异常
// Application 层的业务错误，都应该通过此异常抛出
// Interface 层捕获此类异常，统一输出

@Getter
public class BizExceptioin extends RuntimeException {

    private String code;

    private String desc;

    public BizExceptioin(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
