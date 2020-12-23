package cn.fantasticmao.demo.java.algorithm;

import java.math.BigInteger;

/**
 * FibonacciNumber
 *
 * @author maomao
 * @since 2020-10-28
 */
public class FibonacciNumber {

    public static BigInteger fin(int n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }
        BigInteger a = BigInteger.valueOf(0);
        BigInteger b = BigInteger.valueOf(1);
        BigInteger sum = BigInteger.valueOf(0);
        for (int i = 2; i <= n; i++) {
            sum = a.add(b);
            a = b;
            b = sum;
        }
        return sum;
    }
}
