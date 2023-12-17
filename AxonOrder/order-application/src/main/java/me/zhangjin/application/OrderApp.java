package me.zhangjin.application;


import me.zhangjin.types.command.SubmitOrderCommand;
import me.zhangjin.types.dto.SubmitOrderDTO;

/**
 * <p> 应用服务 编排流程
 */

public interface OrderApp {
    // Application 层的接口入参使用 Command/Query/Event
    // 响应 DTO
    SubmitOrderDTO submitOrder(SubmitOrderCommand submitOrderCommand);
}