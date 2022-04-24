package com.example.study.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * <h3>study</h3>
 * <p></p>
 *
 * @author : ZhangYuJie
 * @date : 2022-04-24 16:53
 **/

public class HashMapTest {
    public static final Map<Object, Object> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    map.put(i, i);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 50; j < 100; j++) {
                    map.put(j, j);
                }
            }
        });
        t1.start();
        t2.start();
        // main线程休眠一会确保pub完成
        Thread.sleep(1000);

        map.forEach((k, v) -> {
            System.err.println("k=" + k + ",v=" + v);
        });
    }
}
