package me.zhangjin.web.soa;

import me.zhangjin.application.listener.DomainCommandMessageListener;
import me.zhangjin.application.listener.DomainEventMessageListener;
import me.zhangjin.domain.acl.logger.OrderLogger;
import me.zhangjin.types.soa.SubmitLineAOrderRequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
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
    public void submitLineAOrderCommand() {
        SubmitLineAOrderRequestType requestType = new SubmitLineAOrderRequestType();
        requestType.setOrderId(123456L);
        requestType.setUid("abc");

        orderLogger.info("--------------------------- SubmitLineAOrderCommand start-----------------------------");
        orderService.submitLineAOrder(requestType);
        orderLogger.info("--------------------------- SubmitLineAOrderCommand  end -----------------------------");
    }

    @Scheduled(initialDelay = 4000)
    public void submitOrderEvent() {
        String content = "{\"desc\":\"Do SubmitOrderEvent\",\"eventVersion\":1,\"occurredOn\":\"2023-12-17T23:04:22.894541300\",\"operatorEid\":\"System\",\"orderId\":123456,\"orderStatus\":\"Submit\",\"processType\":\"LineAProcess\"}";
        String eventtype = "me.zhangjin.domain.event.linea.SubmitOrderEvent";

        orderLogger.info("--------------------------- SubmitOrderEvent start-----------------------------");
        domainEventMessageListener.on(content, eventtype);
        orderLogger.info("--------------------------- SubmitOrderEvent  end -----------------------------");
    }

    @Scheduled(initialDelay = 6000)
    public void sendVenderEvent() {
        String content = "{\"desc\":\"Do SendVenderEvent\",\"eventVersion\":2,\"occurredOn\":\"2023-12-17T23:12:13.594861700\",\"operatorEid\":\"System\",\"orderId\":123456,\"orderStatus\":\"SendOrder\",\"processType\":\"LineAProcess\"}";
        String eventtype ="me.zhangjin.domain.event.linea.SendVenderEvent";

        orderLogger.info("--------------------------- SendVenderEvent start-----------------------------");
        domainEventMessageListener.on(content, eventtype);
        orderLogger.info("--------------------------- SendVenderEvent  end -----------------------------");
    }

    @Scheduled(initialDelay = 8000)
    public void confirmVenderCommand() {
        String content = "{\"venderOrderCode\":\"AXFOIJFIEOFNEF\",\"venderId\":9998,\"orderId\":123456,\"processType\":\"LineAProcess\"}";
        String eventtype ="me.zhangjin.domain.command.linea.ConfirmVenderCommand";

        orderLogger.info("--------------------------- ConfirmVenderCommand start-----------------------------");
        // 监听外部 MQ 触发
        domainCommandMessageListener.on(content, eventtype);
        orderLogger.info("--------------------------- ConfirmVenderCommand  end -----------------------------");
    }
}

