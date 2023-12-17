package me.zhangjin.acl.messing;

import com.alibaba.fastjson.JSON;
import me.zhangjin.domain.acl.logger.OrderLogger;
import me.zhangjin.domain.acl.messaging.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageProducerImpl implements MessageProducer {

    @Autowired
    private OrderLogger orderLogger;

    @Override
    public void send(String topic, Map<String,Object> data) {
        orderLogger.info("send message: %s %s", topic, JSON.toJSONString(data));
    }
}
