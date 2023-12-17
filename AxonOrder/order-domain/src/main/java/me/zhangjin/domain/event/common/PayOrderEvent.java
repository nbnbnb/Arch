package me.zhangjin.domain.event.common;

public class PayOrderEvent extends DomainEvent {

    public PayOrderEvent(Long orderId){
        super(orderId);
    }

    @Override
    public String getDesc() {
        return "Do PayOrderEvent";
    }
}
