package cn.fantasticmao.demo.java.database;

import org.junit.Assert;
import org.junit.Test;

/**
 * UserTest
 *
 * @author maodaohe
 * @since 2023-07-05
 */
public class UserTest {

    @Test
    public void serialize() {
        User tom = new User(1, "Tom", 20, "tom@google.com");
        byte[] bytes = User.toBytes(tom);

        User user = User.fromBytes(bytes);
        Assert.assertEquals(user, tom);
    }

}
