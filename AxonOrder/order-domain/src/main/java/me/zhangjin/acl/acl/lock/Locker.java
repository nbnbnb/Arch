package me.zhangjin.acl.acl.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class Locker {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DistLock distLock;

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
                logger.error("getLock fail", lockKey);
                //  降级，继续处理
                action.accept(obj);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            // 降级，继续处理
            action.accept(obj);
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    public <T,R> R runWithResult(String lockKey, Integer expire, T obj, Function<T,R> func) {
        boolean locked = false;
        DLock lock = null;
        try {
            lock = distLock.getLock(lockKey);
            // 10s 超时
            if (lock != null && (locked = lock.tryLock(expire, TimeUnit.SECONDS)) == Boolean.TRUE) {
                // 正常处理
                return func.apply(obj);
            } else {
                logger.error("getLock fail", lockKey);
                //  降级，继续处理
                return func.apply(obj);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            // 降级，继续处理
            return func.apply(obj);
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

}
