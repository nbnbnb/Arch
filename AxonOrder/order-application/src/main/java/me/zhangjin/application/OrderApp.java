package me.zhangjin.application;


import me.zhangjin.domain.command.linea.LineASubmitOrderCommand;
import me.zhangjin.types.dto.linea.LineASubmitOrderDTO;

/**
 * <p> 应用服务 编排流程
 */

public interface OrderApp {
    // Application 层的接口入参使用 Command/Query/Event
    // 响应 DTO
    LineASubmitOrderDTO lineASubmitOrder(LineASubmitOrderCommand submitLineAOrderCommand);
}
