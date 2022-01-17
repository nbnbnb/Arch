package me.zhangjin.bank.application;

import me.zhangjin.bank.command.TransferCommand;
import me.zhangjin.bank.dto.TransferDTO;
import me.zhangjin.bank.types.Result;

import java.math.BigDecimal;

/**
 * <p> 应用服务 编排流程
 */

public interface TransferService {
    // Application 层的接口入参使用 Command/Query/Event
    // 响应 DTO
    TransferDTO transfer(TransferCommand transferCommand);
}
