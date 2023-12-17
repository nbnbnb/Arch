package me.zhangjin.web.soa;


import me.zhangjin.domain.command.linea.SubmitLineAOrderCommand;
import me.zhangjin.types.ProcessType;
import me.zhangjin.types.Result;
import me.zhangjin.types.dto.SubmitOrderDTO;
import me.zhangjin.types.soa.SubmitLineAOrderRequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import me.zhangjin.application.OrderApp;

// 接口层

// 接口出参：统一返回 Result
// 异常处理：应该捕捉所有异常，避免异常信息的泄漏，可以通过 AOP 统一处理，避免代码里有大量重复代码

@Component
public class OrderService {

    @Autowired
    private OrderApp orderApp;

    public Result<Boolean> submitLineAOrder(SubmitLineAOrderRequestType submitOrderRequestType) {

        SubmitLineAOrderCommand submitOrderCommand = new SubmitLineAOrderCommand();
        submitOrderCommand.setOrderId(submitOrderRequestType.getOrderId());
        submitOrderCommand.setUid(submitOrderRequestType.getUid());
        submitOrderCommand.setProcessType(ProcessType.LineAProcess);

        SubmitOrderDTO res = orderApp.submitOrder(submitOrderCommand);

        if (res.getSuccess()) {
            return Result.success();
        } else {
            return Result.fail();
        }

    }


}
