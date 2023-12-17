package me.zhangjin.domain.event.common;

import me.zhangjin.types.ProcessType;

public class PayOrderEvent extends DomainEvent {

    @Override
    public String getDesc() {
        return "Do PayOrderEvent";
    }
}
