package me.zhangjin.acl.bus;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class SubscriberProcessor implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;
    private CommonMessageBus bus;

    public SubscriberProcessor(CommonMessageBus bus) {
        this.bus = bus;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Subscriber.class);
        for (Object subscriber : beans.values()) {
            bus.subscribe(subscriber);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
