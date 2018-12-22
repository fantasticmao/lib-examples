package priv.mm.base;

import java.util.concurrent.TimeUnit;

/**
 * ObjectAllocateStrategy
 * -XX:+UseSerialGC -Xmx100M -XX:PretenureSizeThreshold=5M -XX:+PrintGCDetails
 * <ol>
 * <li>对象优先分配在 Young Gen 的 Eden</li>
 * <li>大对象直接分配在 Old Gen</li>
 * <li>长期存活对象晋升至 Old Gen</li>
 * <li></li>
 * </ol>
 *
 * @author maodh
 * @since 2018/12/22
 */
public class ObjectAllocateStrategy {
    private static final int _1MB = 1024 * 1024;

    void newSmallObject() {
        byte[] bytes1 = new byte[_1MB];
        byte[] bytes2 = new byte[2 * _1MB];
        byte[] bytes3 = new byte[3 * _1MB];
        byte[] bytes4 = new byte[4 * _1MB];
    }

    void newBigObject() {
        byte[] bytes5 = new byte[10 * _1MB];
    }

    void oldObjectToOldGen() {

    }

    public static void main(String[] args) throws InterruptedException {
        // 开启 jvisualvm，开始监控内存
        TimeUnit.SECONDS.sleep(10);

        ObjectAllocateStrategy strategy = new ObjectAllocateStrategy();
        strategy.newSmallObject();
    }
}
