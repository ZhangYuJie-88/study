package com.example.study.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * <h3>study</h3>
 * <p>抽取公共ThreadLocal容器类：</p>
 *
 * @author : ZhangYuJie
 * @date : 2022-02-27 17:29
 **/

public class MyThreadLocalContext {


    private MyThreadLocalContext() {}

    private static final ThreadLocal<String> parent = new TransmittableThreadLocal<String>();

    public static String get() {
        return parent.get();
    }

    public static void set(String data) {
        parent.set(data);
    }

    public static void remove() {
        parent.remove();
    }
}
