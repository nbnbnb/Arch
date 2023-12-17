package me.zhangjin.application;

import me.zhangjin.application.process.ProcessManagerRouter;
import me.zhangjin.domain.command.linea.SubmitLineAOrderCommand;
import me.zhangjin.types.dto.SubmitLineAOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderAppImpl implements OrderApp {

    @Autowired
    private ProcessManagerRouter managerRouter;

    @Override
    public SubmitLineAOrderDTO submitLineAOrder(SubmitLineAOrderCommand submitLineAOrderCommand) {

        // 由于 dispatcher 可能有多个 handler 可以处理，就有可能多个返回值
        managerRouter.dispatcher(submitLineAOrderCommand);

        // 所以此处，如果要得到返回值，需要特别处理，不够优雅
        // 将返回值赋值到 SubmitLineAOrderCommand 中返回
        return submitLineAOrderCommand.getReturnResult();
    }
}
