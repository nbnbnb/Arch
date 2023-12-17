package me.zhangjin.application.handler.linea.command;


import me.zhangjin.domain.command.linea.LineAConfirmVenderCommand;
import me.zhangjin.domain.acl.repository.OrderRepository;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.types.dto.linea.LineAConfirmVenderDTO;
import me.zhangjin.types.exception.BizExceptioin;
import net.engio.mbassy.listener.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ConfirmVenderCommandHandler {

    @Autowired
    private OrderRepository repository;

    @Handler
    public void confirmVender(LineAConfirmVenderCommand command) {
        // 01 load
        Order order = repository.load(command.getOrderId());

        // 02. verify
        // 业务逻辑检查
        if (order.getOrderStatus() != OrderStatus.SendOrder) {
            throw new BizExceptioin("1003", "confirmVender status check fail");
        }

        // 03 变更快照状态（内存）
        order.confirmVender(command);

        // 4. 保存最新快照，并发送 MQ
        // 发送 ConfirmVenderEvent 到 MQ
        repository.save(order);

        // 5. 设置返回结果
        LineAConfirmVenderDTO res = new LineAConfirmVenderDTO();
        res.setSuccess(true);
        command.setReturnResult(res);

    }

}
