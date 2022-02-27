package com.example.study.ttl;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;

/**
 * <h3>study</h3>
 * <p></p>
 *
 * @author : ZhangYuJie
 * @date : 2022-02-27 16:55
 **/

public class MyThread01 {

    /**
     * ThreadLocal
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main1(String[] args) throws InterruptedException {
        ThreadLocal<String> parent = new ThreadLocal<>();

        parent.set(Thread.currentThread().getName() + "=======hello,myThread00");

        new Thread(() -> {
            try {
                // 设置本线程变量
                parent.set(Thread.currentThread().getName() + "=======hello,myThread01");
                // dosomething
                Thread.sleep(3000);
                // 使用线程变量
                System.out.println(Thread.currentThread().getName() + ":" + parent.get());
                // 清除
                parent.remove();
                // do other thing
                //.....
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread-1").start();

        new Thread(() -> {
            try {
                // 设置本线程变量
                parent.set(Thread.currentThread().getName() + "=======hello,myThread02");
                // dosomething
                Thread.sleep(4000);
                // 使用线程变量
                System.out.println(Thread.currentThread().getName() + ":" + parent.get());
                // 清除
                parent.remove();
                // do other thing
                //.....
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread-2").start();

        System.out.println(Thread.currentThread().getName() + ":" + parent.get());
    }

    /**
     * 父子线程各是各的。
     * 子线程能不能直接获取到父线程的？：   子线程拿不到父线程的。
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main2(String[] args) throws InterruptedException {
        ThreadLocal<String> parent = new ThreadLocal<>();
        parent.set(Thread.currentThread().getName() + "=======hello,myThread");
        new Thread(() -> {
            try {
                // 使用线程变量
                System.out.println(Thread.currentThread().getName() + ":" + parent.get());
                // do other thing
                // .....
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "child-thread").start();
    }

    /**
     * ITL可用情况：
     * JDK提供了InheritableThreadLocal可以让子线程拿到父线程的：
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main3(String[] args) throws InterruptedException {
        ThreadLocal<String> parent = new InheritableThreadLocal<>();

        parent.set(Thread.currentThread().getName() + "=======hello,myThread");

        new Thread(() -> {
            try {
                // 使用线程变量
                System.out.println(Thread.currentThread().getName() + ":" + parent.get());
                // do other thing
                // .....
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "child-thread").start();
    }

    // 0.创建线程池
    private static final ThreadPoolExecutor bizPoolExecutor = new ThreadPoolExecutor
            (2, 2, 1, MINUTES,
            new LinkedBlockingQueue<>(1));

    public static void main(String[] args) throws InterruptedException {
        // 1 创建线程变量
        InheritableThreadLocal<String> parent = new InheritableThreadLocal<>();
        // 2 投递三个任务，让线程池中的线程全部创建。没有这段则可以拿到。
//        for (int i = 0; i < 3; ++i) {
//            bizPoolExecutor.execute(() -> {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
        // 3休眠4s
        Thread.sleep(4000);
        // 4.设置线程变量
        parent.set("value-set-in-parent");

        // 5. 提交任务到线程池
        bizPoolExecutor.execute(() -> {
            try {
                // 5.1访问线程变量
                System.out.println("parent:" + parent.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
