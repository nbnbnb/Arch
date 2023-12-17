package me.zhangjin.acl.config;

import me.zhangjin.acl.bus.CommonMessageBus;
import me.zhangjin.acl.bus.SubscriberProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusConfiguration {

    @Bean
    public CommonMessageBus eventBus() {
        return new CommonMessageBus();
    }

    @Bean
    public SubscriberProcessor subscriberProcessor(CommonMessageBus eventBus) {
        return new SubscriberProcessor(eventBus);
    }
}

