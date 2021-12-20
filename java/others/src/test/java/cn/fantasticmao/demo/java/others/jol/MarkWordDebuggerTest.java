package cn.fantasticmao.demo.java.others.jol;

import org.junit.Test;

import java.io.IOException;

/**
 * MarkWordDebuggerTest
 *
 * @author fantasticmao
 * @since 2021/12/20
 */
public class MarkWordDebuggerTest {
    private MarkWordDebugger debugger;

    public MarkWordDebuggerTest() throws IOException {
        this.debugger = new MarkWordDebugger();
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