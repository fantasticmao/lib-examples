package cn.fantasticmao.demo.java.lang.java15;

import org.junit.Assert;
import org.junit.Test;

/**
 * TextBlockTest
 *
 * @author fantasticmao
 * @since 2024-08-05
 */
public class TextBlockTest {

    @Test
    public void textBlock() {
        String text = """
            hello,
            world
            """;
        Assert.assertEquals("hello,\nworld\n", text);
    }

    @Test
    public void formatted() {
        String name = "fantasticmao";
        String text = "hello, %s".formatted(name);
        Assert.assertEquals("hello, fantasticmao", text);
    }
}
