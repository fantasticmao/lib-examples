package cn.fantasticmao.demo.java.lang.io;

import org.junit.Assert;
import org.junit.Test;

/**
 * IoStreamHookTest
 *
 * @author fantasticmao
 * @since 2025-11-12
 */
public class IoStreamHookTest {

    @Test
    public void example() {
        final IoStreamHook.User user = new IoStreamHook.User("maomao", "123456");
        final byte[] bytes = IoStreamHook.writeObject(user);
        final IoStreamHook.User user2 = IoStreamHook.readObject(bytes);
        Assert.assertEquals(user, user2);
    }
}
