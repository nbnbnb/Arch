package me.zhangjin.bank.application;

import me.zhangjin.bank.types.Result;

import java.math.BigDecimal;

/**
 * 应用服务
 * <p>
 * 编排流程
 */
public interface TransferService {
    Result<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency);
}
