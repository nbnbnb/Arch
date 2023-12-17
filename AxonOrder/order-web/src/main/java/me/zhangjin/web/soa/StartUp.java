package me.zhangjin.web.soa;

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

    public static void main(String[] args) {
        SpringApplication.run(StartUp.class, args);
    }

    @Scheduled(initialDelay = 5000)
    public void runDemo(){
        SubmitLineAOrderRequestType requestType = new SubmitLineAOrderRequestType();
        requestType.setOrderId(123456L);
        requestType.setUid("abc");
        orderService.submitLineAOrder(requestType);
    }
}
