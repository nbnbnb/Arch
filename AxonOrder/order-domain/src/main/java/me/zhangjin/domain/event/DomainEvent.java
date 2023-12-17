package me.zhangjin.domain.event;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class DomainEvent {

    protected long eventVersion;
    protected LocalDateTime occurredOn;
    private String detailDesc;
    private String operatorEid;
    protected Long orderId;

    public DomainEvent() {
        occurredOn = LocalDateTime.now();
        // 默认 System
        // 可以根据请求场景，重新赋值
        operatorEid = "System";
    }

    public abstract String getDesc();

}

