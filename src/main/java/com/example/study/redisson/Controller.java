package com.example.study.redisson;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * <h3>study</h3>
 * <p></p>
 *
 * @author : ZhangYuJie
 * @date : 2022-05-08 19:43
 **/
@Slf4j
@RestController
@RequestMapping("/test")
public class Controller {

    @Autowired
    private RedissonService redissonService;

    @Autowired
    private RedissonClient redissonClient;
    /**
     * 解锁异常情况模拟
     */
    @SneakyThrows
    @PutMapping("/redisson")
    public void TestRedisson(){
        if(redissonClient.getBucket("test:1").isExists()){
            throw new Exception("线程在执行过程中,请稍后再试");
        };
        // 这里是异步方法
        redissonService.testRedisson();
        System.err.println("结束");
    }
}
