package cn.fantasticmao.demo.java.lang.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * HeapOOM
 * -Xms20m -Xmx20m -XX:+UseParallelGC -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 * -XX:+UseSerialGC 虚拟机在 Client 模式下的默认值，使用 Serial + Serial Old 的收集器组合进行内存回收
 * -XX:+UseParNewGC 使用 ParNew + Serial Old 的收集器组合进行内存回收
 * -XX:+UseConcMarkSweepGC 使用 ParNew + CMS + Serial Old 的收集器组合进行内存回收，Serial Old 收集器将作为 CMS 收集器出现 Concurrent Mode Failure 失败后的后备收集器使用
 * -XX:+UseParallelGC 虚拟机在 Server 模式下的默认值，使用 Parallel Scavenge + Parallel Old 的收集器组合进行内存回收
 *
 * @author fantasticmao
 * @since 22/05/2018
 */
public class HeapOOM {

    static class Obj {

    }

    public static void main(String[] args) {
        List<Obj> list = new ArrayList<>();
        while (true) {
            list.add(new Obj());
        }
    }
}
