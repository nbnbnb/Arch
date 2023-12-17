package me.zhangjin.domain.event;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.entity.OrderStatus;

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
