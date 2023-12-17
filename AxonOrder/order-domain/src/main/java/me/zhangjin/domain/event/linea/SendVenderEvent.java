package me.zhangjin.domain.event.linea;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.common.DomainEvent;
import me.zhangjin.types.ProcessType;

@Setter
@Getter
public class SendVenderEvent extends DomainEvent {

    @Override
    public String getDesc() {
        return "Do SendVenderEvent";
    }

    private OrderStatus orderStatus;
}
