package cn.fantasticmao.demo.java.database.redis;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * IdempotentMethodTest
 *
 * @author fantasticmao
 * @since 2020-05-14
 */
public class IdempotentMethodTest {
    private final Jedis jedis;

    public IdempotentMethodTest() {
        this.jedis = new Jedis("localhost", 6379);
    }

    @After
    public void after() {
        this.jedis.close();
    }

    @Test
    public void idempotentMethod() {
        final String uniqueKey = UUID.randomUUID().toString().replaceAll("-", "");
        final AtomicInteger count = new AtomicInteger(0);

        IdempotentMethod idempotentMethod = new IdempotentMethod(this.jedis);
        for (int i = 0; i < 100; i++) {
            idempotentMethod.idempotentMethod(uniqueKey, count::incrementAndGet);
        }
        Assert.assertEquals(1, count.get());
    }

}