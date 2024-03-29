package me.zhangjin.application;

import me.zhangjin.domain.command.TransferCommand;
import me.zhangjin.domain.dto.TransferDTO;

/**
 * <p> 应用服务 编排流程
 */

public interface TransferService {
    // Application 层的接口入参使用 Command/Query/Event
    // 响应 DTO
    TransferDTO transfer(TransferCommand transferCommand);
}
