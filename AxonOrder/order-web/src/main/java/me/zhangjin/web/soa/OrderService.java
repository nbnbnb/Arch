package me.zhangjin.web.soa;


import me.zhangjin.domain.command.linea.LineASubmitOrderCommand;
import me.zhangjin.types.ProcessType;
import me.zhangjin.types.Result;
import me.zhangjin.types.dto.linea.LineASubmitOrderDTO;
import me.zhangjin.types.soa.LineASubmitOrderRequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import me.zhangjin.application.OrderApp;

/**
 * 接口层
 * <p>
 * 规范：Interface 层的 HTTP 和 RPC 接口，返回值为 Result，捕捉所有异常
 * 应该捕捉所有异常，避免异常信息的泄漏，可以通过 AOP 统一处理，避免代码里有大量重复代码
 * 接口出参：统一返回 Result
 * <p>
 * 规范：Application 层的所有接口返回值为 DTO，不负责处理异常
 * 业务异常：抛出对应的异常 Code
 */

@Component
public class OrderService {

    @Autowired
    private OrderApp orderApp;

    public Result<Boolean> lineASubmitOrder(LineASubmitOrderRequestType submitOrderRequestType) {

        LineASubmitOrderCommand submitOrderCommand = new LineASubmitOrderCommand();
        submitOrderCommand.setOrderId(submitOrderRequestType.getOrderId());
        submitOrderCommand.setProcessType(ProcessType.LineAProcess);
        submitOrderCommand.setUid(submitOrderRequestType.getUid());
        LineASubmitOrderDTO res = orderApp.lineASubmitOrder(submitOrderCommand);

        if (res.getSuccess()) {
            return Result.success();
        } else {
            return Result.fail();
        }

    }


}
