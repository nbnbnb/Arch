package me.zhangjin.domain.convert;

import me.zhangjin.domain.command.common.CompleteOrderCommand;
import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.common.CompleteOrderEvent;

import java.time.LocalDateTime;


public class CompleteOrderEventConvert {

    public static CompleteOrderEvent convert(CompleteOrderCommand command){
        CompleteOrderEvent completeOrderEvent = new CompleteOrderEvent();
        completeOrderEvent.setOrderId(command.getOrderId());
        completeOrderEvent.setProcessType(command.getProcessType());
        completeOrderEvent.setCompleteType(command.getCompleteType());
        completeOrderEvent.setCompleteTime(LocalDateTime.now());
        completeOrderEvent.setOrderStatus(OrderStatus.Completed);
        return completeOrderEvent;
    }
}
