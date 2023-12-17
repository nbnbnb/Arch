package me.zhangjin.application;


import me.zhangjin.domain.command.linea.SubmitLineAOrderCommand;
import me.zhangjin.types.dto.SubmitLineAOrderDTO;

/**
 * <p> 应用服务 编排流程
 */

public interface OrderApp {
    // Application 层的接口入参使用 Command/Query/Event
    // 响应 DTO
    SubmitLineAOrderDTO submitLineAOrder(SubmitLineAOrderCommand submitLineAOrderCommand);
}
