package cn.fantasticmao.demo.java.others.hystrix;

import org.junit.Assert;
import org.junit.Test;

/**
 * HelloCommandTest
 *
 * @author fantasticmao
 * @since 2021/12/20
 */
public class HelloCommandTest {

    @Test
    public void success() {
        HelloCommand command = new HelloCommand("MaoMao", false);
        String value = command.execute();
        Assert.assertEquals("Hello MaoMao!", value);
    }

    @Test
    public void failure() {
        HelloCommand command = new HelloCommand("MaoMao", true);
        String value = command.execute();
        Assert.assertEquals("Failure MaoMao!", value);
    }
}