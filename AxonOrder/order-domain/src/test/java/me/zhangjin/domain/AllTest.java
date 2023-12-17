package me.zhangjin.domain;

import org.junit.Test;

public class AllTest {

    @Test
    public void test() {
        String content = "{\"venderOrderCode\":\"AXFOIJFIEOFNEF\",\"venderId\":9998,\"orderId\":123456,\"processType\":\"LineAProcess\"}";
        String eventtype = "me.zhangjin.domain.command.linea.ConfirmVenderCommand";
    }

}
