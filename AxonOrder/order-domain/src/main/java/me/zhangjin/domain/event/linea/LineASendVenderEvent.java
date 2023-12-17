package me.zhangjin.domain.event.linea;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.common.DomainEvent;

@Setter
@Getter
public class LineASendVenderEvent extends DomainEvent {

    @Override
    public String getDesc() {
        return "Do LineASendVenderEvent";
    }

    private OrderStatus orderStatus;
}
