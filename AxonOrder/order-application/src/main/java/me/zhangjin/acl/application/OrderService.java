package me.zhangjin.acl.application;

import me.zhangjin.acl.command.SubmitOrderCommand;
import me.zhangjin.acl.dto.SubmitOrderDTO;

/**
 * <p> 应用服务 编排流程
 */

public interface OrderService {
    // Application 层的接口入参使用 Command/Query/Event
    // 响应 DTO
    SubmitOrderDTO submitOrder(SubmitOrderCommand submitOrderCommand);
}
