package com.example.study.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

/**
 * <h3>study</h3>
 * <p>CompletableFuture</p>
 *
 * @author : ZhangYuJie
 * @date : 2022-03-27 17:06
 **/

public class CompletableFutureTest {

    public static void main1(String[] args) throws ExecutionException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("hello CompletableFuture");
        });
        try {
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("completableFuture end!");

    }

    public static void main2(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("completableFuture end!");

    }

    public static void main3(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
//        多次重复调会失效
        future.complete("World");
        future.complete("World2");
        future.complete("World3");
        future.complete("World4");
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("completableFuture end!");

    }

    public static void main4(String[] args) {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenApply(s -> s + " CompletableFuture")
                .thenApply(String::toUpperCase);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("completableFuture end!");

    }

    public static void main5(String[] args) {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " CompletableFuture"));
        //thenCompose 可以用于组合多个CompletableFuture，将前一个结果作为下一个计算的参数，它们之间存在着先后顺序
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void main6(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "100");
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Double> future = future1.thenCombine(future2, (s, i) -> Double.parseDouble(s + i));
        // 使用thenCombine是将future1 和future2的结果汇总，这一点跟thenCompose()不同。其中future1和future2是并行执行的。
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("completableFuture end!");

    }

    public static void main7(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "100");
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 70);
        CompletableFuture<Void> future = future1.thenAcceptBoth(future2, (s, i) -> {
            System.out.println(s + i);
        });

    }

    public static void main8(String[] args) {
        CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenApply(s -> s + " world")
                .thenApply(s -> s + "\nThis is CompletableFuture demo")
                .thenApply(String::toLowerCase)
                .whenComplete((result, throwable) -> System.out.println(result));

    }

    public static void main9(String[] args) throws ExecutionException, InterruptedException {
        // 使用lamda表达式的写法：
        CompletableFuture<Double> future = CompletableFuture
                .supplyAsync(() -> "100")
                .thenApply(s -> s + "10")
                .handle((s, t) -> s != null ? Double.parseDouble(s) : 0);
        System.err.println(future.get());

    }

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " world")
                .thenApply(s -> s + "\nThis is CompletableFuture demo")
                .thenApply(String::toLowerCase).thenAccept((Consumer) System.out::println);

    }
}
