package com.example.study.jdk11;

import java.util.ArrayList;

/**
 * <h3>study</h3>
 * <p></p>
 *
 * @author : ZhangYuJie
 * @date : 2022-03-20 19:30
 **/

public class EpsilonTest {
    public static void main(String[] args) throws Exception {
        var list = new ArrayList<>();
        boolean flag = true;
        int count = 0;
        while (flag) {
            list.add(new Garbage());
            if (count++ == 500) {
                list.clear();
            }
        }
    }
}

class Garbage {
    private double d1 = 1;
    private double d2 = 2;

    /**
     * GC在清除本对象时会调用该方法
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println(this + " collecting");
    }
}
