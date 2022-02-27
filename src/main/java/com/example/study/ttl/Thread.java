package com.example.study.ttl;

import java.security.AccessControlContext;

/**
 * <h3>study</h3>
 * <p></p>
 *
 * @author : ZhangYuJie
 * @date : 2022-02-27 17:14
 **/

/*public class Thread implements Runnable {
    // 如果单纯使用 ThreadLocal，则 Thread 使用该属性值保存 ThreadLocalMap
    ThreadLocal.ThreadLocalMap threadLocals = null;
    // 否则使用该属性值
    ThreadLocal.ThreadLocalMap inheritableThreadLocals = null;

    //inheritThreadLocals默认true
    private void init(ThreadGroup g, Runnable target, String name,
                      long stackSize, AccessControlContext acc,
                      boolean inheritThreadLocals) {
        Thread parent = currentThread();
          …………
        if (inheritThreadLocals && parent.inheritableThreadLocals != null)
            this.inheritableThreadLocals =
                    //Thread的init方法创建新线程时把父线程的拿了过来
                    ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);

    }
}*/
