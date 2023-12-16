package me.zhangjin.order.handler.order;


import me.zhangjin.order.acl.repository.OrderRepository;
import me.zhangjin.order.command.SubmitOrderCommand;
import me.zhangjin.order.domain.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubmitOrderCommandHandler {

    @Autowired
    private OrderRepository repository;

    public void handle(SubmitOrderCommand command) {

        // 使用方式：
        // 在 handle 方法中，执行业务逻辑处理
        // 将处理后的结果，保存到 Command 中
        // 然后调用 Entity 提供的方法（动作），将 Command 转换为对应的 DomainEvent
        // 方法会将 DomainEvent apply 到 Entity 中，完成对象状态的修改
        // 最后，调用 repository.save 方法，将最新的快照保存到 DB，同时发送 DomainEvent 到 MQ

        // 1. 准备数据，加载快照最新版本
        Order master = repository.load(command.getOrderId());

        // 2. 执行业务逻辑（风控调用接口）
        // verify

        // 3. 变更快照状态（内存）
        master.submitOrder(command);

        // 4. 保存最新快照，并发送 MQ
        repository.save(master);

    }
}
