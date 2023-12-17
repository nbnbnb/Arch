package me.zhangjin.web.soa;

import me.zhangjin.application.listener.DomainCommandMessageListener;
import me.zhangjin.application.listener.DomainEventMessageListener;
import me.zhangjin.domain.acl.logger.OrderLogger;
import me.zhangjin.types.soa.LineASubmitOrderRequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication(scanBasePackages = "me.zhangjin")
@EnableScheduling
public class StartUp {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderLogger orderLogger;

    @Autowired
    private DomainEventMessageListener domainEventMessageListener;

    @Autowired
    private DomainCommandMessageListener domainCommandMessageListener;

    public static void main(String[] args) {
        SpringApplication.run(StartUp.class, args);
    }

    @Scheduled(initialDelay = 2000)
    public void lineASubmitOrderCommand() {
        LineASubmitOrderRequestType requestType = new LineASubmitOrderRequestType();
        requestType.setOrderId(123456L);
        requestType.setUid("abc");

        orderLogger.info("--------------------------- LineASubmitOrderCommand start-----------------------------");
        orderService.lineASubmitOrder(requestType);
        orderLogger.info("--------------------------- LineASubmitOrderCommand  end -----------------------------");
    }

    @Scheduled(initialDelay = 4000)
    public void lineASubmitOrderEvent() {
        String content = "{\"desc\":\"Do LineASubmitOrderEvent\",\"eventVersion\":1,\"occurredOn\":\"2023-12-17T23:04:22.894541300\",\"operatorEid\":\"System\",\"orderId\":123456,\"orderStatus\":\"Submit\",\"processType\":\"LineAProcess\"}";
        String eventtype = "me.zhangjin.domain.event.linea.LineASubmitOrderEvent";

        orderLogger.info("--------------------------- LineASubmitOrderEvent start-----------------------------");
        domainEventMessageListener.on(content, eventtype);
        orderLogger.info("--------------------------- LineASubmitOrderEvent  end -----------------------------");
    }

    @Scheduled(initialDelay = 6000)
    public void lineASendVenderEvent() {
        String content = "{\"desc\":\"Do SendVenderEvent\",\"eventVersion\":2,\"occurredOn\":\"2023-12-17T23:12:13.594861700\",\"operatorEid\":\"System\",\"orderId\":123456,\"orderStatus\":\"SendOrder\",\"processType\":\"LineAProcess\"}";
        String eventtype = "me.zhangjin.domain.event.linea.LineASendVenderEvent";

        orderLogger.info("--------------------------- LineASendVenderEvent start-----------------------------");
        domainEventMessageListener.on(content, eventtype);
        orderLogger.info("--------------------------- LineASendVenderEvent  end -----------------------------");
    }

    @Scheduled(initialDelay = 8000)
    public void lineAConfirmVenderCommand() {
        String content = "{\"venderOrderCode\":\"AXFOIJFIEOFNEF\",\"venderId\":9998,\"orderId\":123456,\"processType\":\"LineAProcess\"}";
        String eventtype = "me.zhangjin.domain.command.linea.LineAConfirmVenderCommand";

        orderLogger.info("--------------------------- LineAConfirmVenderCommand start-----------------------------");
        // 监听外部 DomainCommand MQ 触发
        domainCommandMessageListener.on(content, eventtype);
        orderLogger.info("--------------------------- LineAConfirmVenderCommand  end -----------------------------");
    }

    @Scheduled(initialDelay = 10000)
    public void lineAConfirmVenderEvent() {
        String content = "{\"desc\":\"Do ConfirmVenderEvent\",\"eventVersion\":3,\"occurredOn\":\"2023-12-18T00:03:38.191954200\",\"operatorEid\":\"System\",\"orderId\":123456,\"orderStatus\":\"ConfirmOrder\",\"processType\":\"LineAProcess\",\"venderId\":9998,\"venderOrderCode\":\"AXFOIJFIEOFNEF\"}";
        String eventtype = "me.zhangjin.domain.event.linea.LineAConfirmVenderEvent";

        orderLogger.info("--------------------------- LineAConfirmVenderEvent start-----------------------------");
        // 监听内部 DomainEvent MQ 触发
        domainEventMessageListener.on(content, eventtype);
        orderLogger.info("--------------------------- LineAConfirmVenderEvent  end -----------------------------");
    }

    @Scheduled(initialDelay = 12000)
    public void commonCompleteOrderCommand() {
        String content = "{\"desc\":\"Do ConfirmVenderEvent\",\"eventVersion\":3,\"occurredOn\":\"2023-12-18T00:03:38.191954200\",\"operatorEid\":\"System\",\"orderId\":123456,\"orderStatus\":\"ConfirmOrder\",\"processType\":\"LineAProcess\",\"venderId\":9998,\"venderOrderCode\":\"AXFOIJFIEOFNEF\"}";
        String eventtype = "me.zhangjin.domain.command.common.CommonCompleteOrderCommand";

        orderLogger.info("--------------------------- CommonCompleteOrderCommand start-----------------------------");
        // 监听外部 DomainCommand MQ 触发
        domainCommandMessageListener.on(content, eventtype);
        orderLogger.info("--------------------------- CommonCompleteOrderCommand  end -----------------------------");
    }
}

