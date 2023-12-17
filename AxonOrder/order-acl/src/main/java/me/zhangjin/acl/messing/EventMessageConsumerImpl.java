package me.zhangjin.acl.messing;

import me.zhangjin.acl.acl.logger.OrderLogger;
import me.zhangjin.acl.acl.messaging.EventMessageConsumer;
import me.zhangjin.acl.domain.entity.event.AbstractEvent;
import me.zhangjin.acl.handler.ProcessManagerRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventMessageConsumerImpl implements EventMessageConsumer {

    @Autowired
    private OrderLogger orderLogger;

    @Autowired
    private ProcessManagerRouter managerRouter;

    @Override
    public void on(AbstractEvent event) {
        managerRouter.dispatcher(event);
    }
}
