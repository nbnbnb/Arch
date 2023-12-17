package me.zhangjin.domain.event.linea;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.common.DomainEvent;

@Getter
@Setter
public class LineAConfirmVenderEvent extends DomainEvent {

    @Override
    public String getDesc() {
        return "Do LineAConfirmVenderEvent";
    }

    private String venderOrderCode;

    private Long venderId;

    private OrderStatus orderStatus;
}
