package cn.fantasticmao.demo.java.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

/**
 * FibonacciNumberTest
 *
 * @author maomao
 * @since 2020-10-28
 */
public class FibonacciNumberTest {

    @Test
    public void fin() {
        BigInteger actual = FibonacciNumber.fin(100);
        BigInteger expected = new BigInteger("354224848179261915075");
        Assert.assertEquals(expected, actual);
    }
}