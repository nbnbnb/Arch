package me.zhangjin.domain.convert;

import me.zhangjin.domain.command.common.CommonCompleteOrderCommand;
import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.common.CommonCompleteOrderEvent;

import java.time.LocalDateTime;


public class CompleteOrderEventConvert {

    public static CommonCompleteOrderEvent convert(CommonCompleteOrderCommand command){
        CommonCompleteOrderEvent completeOrderEvent = new CommonCompleteOrderEvent();
        completeOrderEvent.setOrderId(command.getOrderId());
        completeOrderEvent.setProcessType(command.getProcessType());
        completeOrderEvent.setCompleteType(command.getCompleteType());
        completeOrderEvent.setCompleteTime(LocalDateTime.now());
        completeOrderEvent.setOrderStatus(OrderStatus.Completed);
        return completeOrderEvent;
    }
}
