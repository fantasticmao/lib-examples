package cn.fantasticmao.demo.java.algorithm;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * FibonacciNumber
 *
 * @author maomao
 * @since 2020-10-28
 */
public class FibonacciNumber {
    private static final Map<Integer, BigInteger> CACHE = new HashMap<>();

    public static BigInteger fin(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }
        if (CACHE.containsKey(n)) {
            return CACHE.get(n);
        }
        final BigInteger result = fin(n - 1).add(fin(n - 2));
        CACHE.put(n, result);
        return result;
    }
}
