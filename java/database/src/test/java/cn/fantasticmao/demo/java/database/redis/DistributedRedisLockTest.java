package cn.fantasticmao.demo.java.database.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

/**
 * DistributedRedisLockTest
 *
 * @author fantasticmao
 * @since 2020-05-14
 */
@Slf4j
public class DistributedRedisLockTest {
    private final int parallelSize;
    private final JedisPool jedisPool;

    public DistributedRedisLockTest() {
        this.parallelSize = 10;

        GenericObjectPoolConfig<Jedis> poolConfig = new JedisPoolConfig();
        // JedisPool 连接池大小需要 >= 并发线程数大小，避免从 JedisPool 获取连接时阻塞
        poolConfig.setMaxTotal(parallelSize);
        this.jedisPool = new JedisPool(poolConfig, "localhost", 6379);
    }

    @After
    public void after() {
        this.jedisPool.close();
    }

    @Test
    public void testRedisLock() throws InterruptedException {
        final int repeatTimes = 10;

        AtomicInteger count = new AtomicInteger(0);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < this.parallelSize; i++) {
            Thread t = Thread.startVirtualThread(() -> {
                Lock lock = new DistributedRedisLock(this.jedisPool, "lock");
                lock.lock();
                try {
                    for (int j = 0; j < repeatTimes; j++) {
                        log.info("thread id: {}, count: {}", Thread.currentThread().threadId(), count.getAndIncrement());
                    }
                } finally {
                    lock.unlock();
                }
            });
            threads.add(t);
        }
        for (Thread t : threads) {
            t.join();
        }
        Assert.assertEquals(this.parallelSize * repeatTimes, count.get());
    }

}
