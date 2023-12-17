package me.zhangjin.domain.event.common;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.entity.CompleteType;
import me.zhangjin.domain.entity.OrderStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommonCompleteOrderEvent extends DomainEvent {

    @Override
    public String getDesc() {
        return "Do CommonCompleteOrderEvent";
    }

    // 1 自动完成
    // 2 手动完成
    private CompleteType completeType;

    private LocalDateTime completeTime;

    private OrderStatus orderStatus;
}
