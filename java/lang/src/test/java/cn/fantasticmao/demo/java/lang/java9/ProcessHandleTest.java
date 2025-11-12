package cn.fantasticmao.demo.java.lang.java9;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * ProcessHandleTest
 *
 * @author fantasticmao
 * @since 2022/3/2
 */
@Slf4j
public class ProcessHandleTest {

    @Test
    public void processHandle() {
        ProcessHandle processHandle = ProcessHandle.current();
        log.info("pid: {}", processHandle.pid());
        log.info("info: {}", processHandle.info());
    }
}
