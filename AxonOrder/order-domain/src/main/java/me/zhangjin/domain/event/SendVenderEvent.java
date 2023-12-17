package me.zhangjin.domain.event;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.entity.OrderStatus;

@Setter
@Getter
public class SendVenderEvent extends DomainEvent {

    public SendVenderEvent(Long orderId){
        super(orderId);
    }

    @Override
    public String getDesc() {
        return "Do SendVenderEvent";
    }

    private OrderStatus orderStatus;
}
