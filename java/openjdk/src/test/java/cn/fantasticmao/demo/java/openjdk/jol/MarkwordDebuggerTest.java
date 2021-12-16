package cn.fantasticmao.demo.java.openjdk.jol;

import org.junit.Test;

import java.io.IOException;

/**
 * MarkwordDebuggerTest
 *
 * @author fantasticmao
 * @since 2020-05-15
 */
public class MarkwordDebuggerTest {
    private MarkwordDebugger debugger;

    public MarkwordDebuggerTest() throws IOException {
        this.debugger = new MarkwordDebugger();
    }

    @Test
    public void thinLocking() {
        this.debugger.thinLocking();
    }

    @Test
    public void biasedLocking() throws InterruptedException {
        this.debugger.biasedLocking();
    }

    @Test
    public void fatLocking() throws InterruptedException {
        this.debugger.fatLocking();
    }
}