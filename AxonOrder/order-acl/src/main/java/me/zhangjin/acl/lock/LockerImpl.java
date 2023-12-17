package me.zhangjin.acl.lock;

import me.zhangjin.domain.acl.lock.Locker;

import me.zhangjin.domain.acl.logger.OrderLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class LockerImpl implements Locker {

    @Autowired
    private OrderLogger orderLogger;

    @Autowired
    private DistLock distLock;

    @Override
    public <T> void run(String lockKey, Integer expire, T obj, Consumer<T> action) {
        boolean locked = false;
        DLock lock = null;
        try {
            lock = distLock.getLock(lockKey);
            // 10s 超时
            if (lock != null && (locked = lock.tryLock(expire, TimeUnit.SECONDS)) == Boolean.TRUE) {
                // 正常处理
                action.accept(obj);
            } else {
                orderLogger.error("getLock fail: " + lockKey);
                //  降级，继续处理
                action.accept(obj);
            }
        } catch (Exception ex) {
            orderLogger.error("getLock fail",ex);
            // 降级，继续处理
            action.accept(obj);
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    @Override
    public <T, R> R runWithResult(String lockKey, Integer expire, T obj, Function<T, R> func) {
        boolean locked = false;
        DLock lock = null;
        try {
            lock = distLock.getLock(lockKey);
            // 10s 超时
            if (lock != null && (locked = lock.tryLock(expire, TimeUnit.SECONDS)) == Boolean.TRUE) {
                // 正常处理
                return func.apply(obj);
            } else {
                orderLogger.error("getLock fail: " + lockKey);
                //  降级，继续处理
                return func.apply(obj);
            }
        } catch (Exception ex) {
            orderLogger.error("getLock fail",ex);
            // 降级，继续处理
            return func.apply(obj);
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

}


