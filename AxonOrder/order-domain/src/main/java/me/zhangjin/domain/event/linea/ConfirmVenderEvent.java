package me.zhangjin.domain.event.linea;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.common.DomainEvent;
import me.zhangjin.types.ProcessType;

@Getter
@Setter
public class ConfirmVenderEvent extends DomainEvent {

    @Override
    public String getDesc() {
        return "Do ConfirmVenderEvent";
    }

    private String venderOrderCode;

    private Long venderId;

    private OrderStatus orderStatus;
}
