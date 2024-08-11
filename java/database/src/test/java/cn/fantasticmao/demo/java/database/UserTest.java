package cn.fantasticmao.demo.java.database;

import org.junit.Assert;
import org.junit.Test;

/**
 * UserTest
 *
 * @author fantasticmao
 * @since 2023-07-05
 */
public class UserTest {

    @Test
    public void serialize() {
        byte[] bytes = User.toBytes(User.Tom);
        User user = User.fromBytes(bytes);
        Assert.assertEquals(User.Tom, user);
    }

}
