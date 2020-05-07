package cn.fantasticmao.demo.java.database.redis;

import cn.fantasticmao.demo.java.algorithm.Snowflake;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

/**
 * Idempotent Method
 *
 * @author maomao
 * @since 2020-01-11
 */
public class IdempotentMethod {
    private int count = 0;
    private Jedis jedis;

    @Before
    public void before() {
        this.jedis = new Jedis("localhost", 6379);
    }

    @After
    public void after() {
        jedis.close();
    }

    private boolean idempotentMethod(long uniqueId) {
        final String statusCode = jedis.set("idempotent-method:" + uniqueId, "", SetParams.setParams().ex(5).nx());
        if ("ok".equalsIgnoreCase(statusCode)) {
            count++;
        }
        return true;
    }


    @Test
    public void test() {
        final long uniqueId = Snowflake.getInstance(99).nextId();
        for (int i = 0; i < 100; i++) {
            idempotentMethod(uniqueId);
        }
        System.out.println("count: " + count);
    }

}
