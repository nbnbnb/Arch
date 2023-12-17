package me.zhangjin.domain.convert;

import me.zhangjin.domain.command.CompleteOrderCommand;
import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.CompleteOrderEvent;
import sun.util.resources.LocaleData;

import java.time.LocalDateTime;


public class CompleteOrderEventConvert {

    public static CompleteOrderEvent convert(CompleteOrderCommand command){
        CompleteOrderEvent completeOrderEvent = new CompleteOrderEvent(command.getOrderId());
        completeOrderEvent.setCompleteType(command.getCompleteType());
        completeOrderEvent.setCompleteTime(LocalDateTime.now());
        completeOrderEvent.setOrderStatus(OrderStatus.Completed);
        return completeOrderEvent;
    }
}
