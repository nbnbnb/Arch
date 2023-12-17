package me.zhangjin.domain.acl.logger;

public interface OrderLogger {
    void info(String message);

    void info(String message, Object... args);

    void error(String message);

    void error(String message,Object... args);

    void error(String message,Exception exception);
}
