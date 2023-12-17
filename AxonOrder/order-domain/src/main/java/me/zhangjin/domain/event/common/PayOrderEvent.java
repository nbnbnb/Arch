package me.zhangjin.domain.event.common;

import me.zhangjin.types.ProcessType;

public class PayOrderEvent extends DomainEvent {

    public PayOrderEvent(Long orderId, ProcessType processType) {
        super(orderId, processType);
    }

    @Override
    public String getDesc() {
        return "Do PayOrderEvent";
    }
}
