package me.zhangjin.order.config;

import me.zhangjin.order.bus.CommonMessageBus;
import me.zhangjin.order.bus.SubscriberProcessor;
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

