package me.zhangjin.bank.application;

import me.zhangjin.bank.types.Result;

import java.math.BigDecimal;

public interface TransferService {
    Result<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency);
}
