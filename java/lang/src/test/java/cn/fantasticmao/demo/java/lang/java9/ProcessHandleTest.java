package cn.fantasticmao.demo.java.lang.java9;

import org.junit.Test;

/**
 * ProcessHandleTest
 *
 * @author fantasticmao
 * @since 2022/3/2
 */
public class ProcessHandleTest {

    @Test
    public void processHandle() {
        ProcessHandle processHandle = ProcessHandle.current();
        System.out.println("pid: " + processHandle.pid());
        System.out.println("info: " + processHandle.info());
    }
}
