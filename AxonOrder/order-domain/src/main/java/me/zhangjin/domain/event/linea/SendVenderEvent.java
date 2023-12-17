package me.zhangjin.domain.event.linea;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.common.DomainEvent;

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
