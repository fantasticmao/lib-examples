package cn.fantasticmao.demo.java.designpattern.observer;

import org.junit.Test;

/**
 * ObserverTest
 *
 * @author maomao
 * @since 2020-05-14
 */
public class ObserverTest {

    @Test
    public void test() {
        Message message = new Message();
        Tom tom = new Tom(message);
        Sam sam = new Sam(message);
        message.sendMessage("Good Evening");
    }

}