package me.zhangjin.order.web;

import me.zhangjin.order.application.TransferService;
import me.zhangjin.order.command.TransferCommand;
import me.zhangjin.order.dto.TransferDTO;
import me.zhangjin.order.types.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

// 接口层

@Component
public class TransferController {

    @Autowired
    private TransferService transferService;

    // 接口出参：统一返回 Result
    // 异常处理：应该捕捉所有异常，避免异常信息的泄漏，可以通过 AOP 统一处理，避免代码里有大量重复代码
    public Result<Boolean> transfer(String targetAccountNumber, BigDecimal amount, HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        TransferCommand transferCommand = new TransferCommand();
        transferCommand.setTargetAmount(amount);
        transferCommand.setSourceUserId(userId);
        transferCommand.setTargetAccountNumber(targetAccountNumber);
        transferCommand.setTargetCurrency("CNY");

        TransferDTO res = transferService.transfer(transferCommand);

        if (res.getSuccess()) {
            return Result.success();
        } else {
            return Result.fail();
        }

    }

}
