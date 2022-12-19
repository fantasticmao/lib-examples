package cn.fantasticmao.demo.java.lang.java11;

import org.junit.Assert;
import org.junit.Test;

/**
 * StringTest
 *
 * @author fantasticmao
 * @since 2022/3/1
 */
public class StringTest {

    @Test
    public void isBlank() {
        Assert.assertTrue("  ".isBlank());
    }

    @Test
    public void lines() {
        var list = "line1\nline2\nline3".lines()
            .toList();
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void repeat() {
        var str = "hello,".repeat(3);
        Assert.assertEquals("hello,hello,hello,", str);
    }

    @Test
    public void strip() {
        Assert.assertEquals("#hello#", "#" + " hello ".trim() + "#");
        Assert.assertEquals("#hello#", "#" + " hello ".strip() + "#");
        Assert.assertEquals("#hello #", "#" + " hello ".stripLeading() + "#");
        Assert.assertEquals("# hello#", "#" + " hello ".stripTrailing() + "#");
    }
}
