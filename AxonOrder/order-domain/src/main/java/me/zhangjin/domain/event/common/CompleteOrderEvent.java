package me.zhangjin.domain.event.common;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.types.ProcessType;

import java.time.LocalDateTime;

@Getter
@Setter
public class CompleteOrderEvent extends DomainEvent {

    public CompleteOrderEvent(Long orderId, ProcessType processType) {
        super(orderId, processType);
    }

    @Override
    public String getDesc() {
        return "Do CompleteOrderEvent";
    }

    // 1 自动完成
    // 2 手动完成
    private Integer completeType;

    private LocalDateTime completeTime;

    private OrderStatus orderStatus;
}
