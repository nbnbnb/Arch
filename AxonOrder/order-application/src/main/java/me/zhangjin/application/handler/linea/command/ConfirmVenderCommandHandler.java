package me.zhangjin.application.handler.linea.command;


import me.zhangjin.domain.command.linea.ConfirmVenderCommand;
import me.zhangjin.domain.acl.repository.OrderRepository;
import me.zhangjin.domain.entity.Order;
import net.engio.mbassy.listener.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ConfirmVenderCommandHandler {

    @Autowired
    private OrderRepository repository;

    @Handler
    public void confirmVender(ConfirmVenderCommand command) {
        // 01 load
        Order order = repository.load(command.getOrderId());

        // 02. verify
        // 业务逻辑检查

        // 03 变更快照状态（内存）
        order.confirmVender(command);

        // 4. 保存最新快照，并发送 MQ
        repository.save(order);
    }

}
