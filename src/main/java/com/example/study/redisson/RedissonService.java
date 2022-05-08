package com.example.study.redisson;

import lombok.SneakyThrows;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <h3>study</h3>
 * <p></p>
 *
 * @author : ZhangYuJie
 * @date : 2022-05-08 19:46
 **/
@Service
public class RedissonService {

    @Autowired
    private RedissonClient redissonClient;

    @SneakyThrows
    @Async
    public void testRedisson() {
        RLock lock = redissonClient.getLock("test:1");
        // 不设置锁时长（tryLock如果设置锁时长看门狗会失效），利用redis的看门狗默认30s，10s自动续期到30s，等待异步方法执行结束释放锁
        lock.tryLock(5, TimeUnit.SECONDS);
        try {
            // 任何业务代码
            System.err.println("testRedisson");
            Thread.sleep(100000);
        } finally {
            lock.unlock();
        }
    }
}
