package me.zhangjin.order.web;


import me.zhangjin.order.command.SubmitOrderCommand;
import me.zhangjin.order.dto.SubmitOrderDTO;
import me.zhangjin.order.soa.SubmitOrderRequestType;
import me.zhangjin.order.types.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import me.zhangjin.order.application.OrderService;

// 接口层

@Component
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 接口出参：统一返回 Result
    // 异常处理：应该捕捉所有异常，避免异常信息的泄漏，可以通过 AOP 统一处理，避免代码里有大量重复代码
    public Result<Boolean> submitOrder(SubmitOrderRequestType submitOrderRequestType) {

        SubmitOrderCommand submitOrderCommand = new SubmitOrderCommand();

        SubmitOrderDTO res = orderService.submitOrder(submitOrderCommand);

        if (res.getSuccess()) {
            return Result.success();
        } else {
            return Result.fail();
        }

    }


}
