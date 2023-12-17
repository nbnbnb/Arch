package me.zhangjin.application.process;

import me.zhangjin.domain.acl.lock.Locker;
import me.zhangjin.domain.acl.repository.OrderRepository;
import me.zhangjin.domain.command.common.DomainCommand;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.domain.event.common.DomainEvent;
import me.zhangjin.types.ProcessType;
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
    private Locker locker;

    public void dispatcher(DomainEvent event) {
        // 找到对应的流程处理器
        AbstractProcessManager manager = getProcessManager(event.getProcessType());

        // 使用分布式锁
        String lockKey = event.getOrderId().toString();
        locker.run(lockKey, 10, event, manager::dispatch);

    }

    public void dispatcher(DomainCommand command) {
        // 找到对应的流程处理器
        AbstractProcessManager manager = getProcessManager(command.getProcessType());

        // 使用分布式锁
        String lockKey = command.getOrderId().toString();
        locker.run(lockKey, 10, command, manager::dispatch);

    }

    private AbstractProcessManager getProcessManager(ProcessType processType) {
        Map<String, AbstractProcessManager> managers = applicationContext.getBeansOfType(AbstractProcessManager.class);
        for (AbstractProcessManager manager : managers.values()) {
            if (manager.getProcessType() == processType) {
                return manager;
            }
        }
        throw new RuntimeException(String.format("process manager not found !!,process type : %s", processType));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
