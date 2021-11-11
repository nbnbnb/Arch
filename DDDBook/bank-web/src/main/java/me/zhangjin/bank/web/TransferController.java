package me.zhangjin.bank.web;

import me.zhangjin.bank.application.TransferService;
import me.zhangjin.bank.types.Result;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class TransferController {

    private TransferService transferService;

    public Result<Boolean> transfer(String targetAccountNumber, BigDecimal amount, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        return transferService.transfer(userId, targetAccountNumber, amount, "CNY");
    }

}
