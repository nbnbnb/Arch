package me.zhangjin.acl.application.process;

import me.zhangjin.acl.acl.lock.Locker;
import me.zhangjin.acl.acl.repository.OrderRepository;
import me.zhangjin.acl.domain.entity.Order;
import me.zhangjin.acl.domain.event.AbstractEvent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProcessManagerRouter implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Locker locker;

    public void dispatcher(AbstractEvent event) {
        Order order = orderRepository.load(event.getOrderId());

        // 找到对应的流程处理器
        AbstractProcessManager manager = getProcessManager(order);

        // 使用分布式锁
        String lockKey = event.getOrderId().toString();
        locker.run(lockKey, 10, event, manager::dispatch);

    }

    private AbstractProcessManager getProcessManager(Order order) {
        Map<String, AbstractProcessManager> managers = applicationContext.getBeansOfType(AbstractProcessManager.class);
        for (AbstractProcessManager manager : managers.values()) {
            if (manager.getProcessType() == order.getProcessType()) {
                return manager;
            }
        }
        throw new RuntimeException(String.format("process manager not found !!,process type : %s", order.getProcessType()));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
