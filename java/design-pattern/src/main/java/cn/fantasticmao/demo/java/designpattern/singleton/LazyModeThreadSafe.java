package cn.fantasticmao.demo.java.designpattern.singleton;

import javax.annotation.concurrent.ThreadSafe;

/**
 * LazyModeThreadSafe
 *
 * @author maodh
 * @since 2019/1/4
 */
@ThreadSafe
public class LazyModeThreadSafe {
    private static volatile LazyModeThreadSafe instance = null;

    private LazyModeThreadSafe() {
    }

    public static LazyModeThreadSafe getInstance() {
        if (instance == null) {
            synchronized (LazyModeThreadSafe.class) {
                if (instance == null) {
                    instance = new LazyModeThreadSafe();
                }
            }
        }
        return instance;
    }
}
