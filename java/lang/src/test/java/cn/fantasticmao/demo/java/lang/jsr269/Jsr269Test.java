package cn.fantasticmao.demo.java.lang.jsr269;

import org.junit.Assert;
import org.junit.Test;

/**
 * SoonDeprecatedTest
 *
 * @author fantasticmao
 * @since 2022-12-19
 */
@SoonDeprecated(version = "endless")
public class Jsr269Test {

    @Test
    @SoonDeprecated(version = "endless")
    public void run() {
        Assert.assertTrue(true);
    }
}
