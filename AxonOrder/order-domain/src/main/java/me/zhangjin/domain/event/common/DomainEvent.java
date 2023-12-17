package me.zhangjin.domain.event.common;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.types.ProcessType;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class DomainEvent {

    protected ProcessType processType;
    protected Long orderId;

    protected long eventVersion;
    protected LocalDateTime occurredOn;
    protected String detailDesc;
    protected String operatorEid;


    public DomainEvent(Long orderId, ProcessType processType) {
        this.occurredOn = LocalDateTime.now();
        // 默认 System
        // 可以根据请求场景，重新赋值
        this.operatorEid = "System";
        this.orderId = orderId;
        this.processType = processType;
    }

    public abstract String getDesc();

}

