package me.zhangjin.order.handler;

import me.zhangjin.order.acl.lock.DLock;
import me.zhangjin.order.acl.lock.DistLock;
import me.zhangjin.order.acl.repository.OrderRepository;
import me.zhangjin.order.domain.entity.Order;
import me.zhangjin.order.domain.entity.event.AbstractEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class ProcessManagerRouter implements ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private ApplicationContext applicationContext;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DistLock distLock;

    public void dispatcher(AbstractEvent event) {
        Order order = orderRepository.load(event.getOrderId());
        AbstractProcessManager manager = getProcessManager(order);

        // 使用分布式锁
        doLockDispatch(manager, event);
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

    private void doLockDispatch(AbstractProcessManager manager, AbstractEvent event) {

        // TODO 埋点锁失败场景

        boolean locked = false;
        DLock lock = null;
        try {
            lock = distLock.getLock(event.getOrderId().toString());
            // 10s 超时
            if (lock != null && (locked = lock.tryLock(10, TimeUnit.SECONDS)) == Boolean.TRUE) {
                // 正常处理
                manager.dispatch(event);
            } else {
                logger.error("getLock fail", event.getOrderId().toString());
                //  降级，继续处理
                manager.dispatch(event);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            // 降级，继续处理
            manager.dispatch(event);
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
