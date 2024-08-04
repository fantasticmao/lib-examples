package cn.fantasticmao.demo.java.lang.java15;

import org.junit.Assert;
import org.junit.Test;

/**
 * StringTest
 *
 * @author fantasticmao
 * @since 2024-08-05
 */
public class StringTest {

    @Test
    public void textBlock() {
        String text = """
            hello,
            world
            """;
        Assert.assertEquals("hello,\nworld\n", text);
    }
}
