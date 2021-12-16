package cn.fantasticmao.demo.java.designpattern.singleton;

import javax.annotation.concurrent.ThreadSafe;

/**
 * HungryMode
 *
 * @author fantasticmao
 * @since 2019/1/4
 */
@ThreadSafe
public class HungryMode {
    private static final HungryMode INSTANCE = new HungryMode();

    private HungryMode() {
    }

    public static HungryMode getInstance() {
        return INSTANCE;
    }
}
