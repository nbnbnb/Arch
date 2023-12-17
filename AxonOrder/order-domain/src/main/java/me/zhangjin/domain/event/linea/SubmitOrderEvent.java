package me.zhangjin.domain.event.linea;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.common.DomainEvent;
import me.zhangjin.types.ProcessType;

@Setter
@Getter
public class SubmitOrderEvent extends DomainEvent {

    public SubmitOrderEvent(Long orderId, ProcessType processType) {
        super(orderId, processType);
    }

    @Override
    public String getDesc() {
        return "Do SubmitOrderEvent";
    }

    private OrderStatus orderStatus;
}
