package me.zhangjin.acl.logger;

import me.zhangjin.domain.acl.logger.OrderLogger;
import org.springframework.stereotype.Component;

@Component
public class OrderLoggerImpl implements OrderLogger {

    @Override
    public void info(String message) {
        System.out.println(message);
    }

    @Override
    public void info(String message, Object... args) {
        System.out.println(String.format(message, args));
    }

    @Override
    public void error(String message) {
        System.err.println(message);
    }

    @Override
    public void error(String message, Object... args) {
        System.err.println(String.format(message, args));
    }

    @Override
    public void error(String message, Exception exception) {
        System.err.println(message + " message: " + exception.getMessage());
    }
}
