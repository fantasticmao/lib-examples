package cn.fantasticmao.demo.java.designpattern.singleton;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * LazyMode
 *
 * @author maodh
 * @since 2019/1/4
 */
@NotThreadSafe
public class LazyMode {
    private static LazyMode instance = null;

    private LazyMode() {
    }

    public static LazyMode getInstance() {
        return instance == null ? instance = new LazyMode() : instance;
    }
}
