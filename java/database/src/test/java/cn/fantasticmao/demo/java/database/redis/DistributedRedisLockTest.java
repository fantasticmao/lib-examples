package cn.fantasticmao.demo.java.database.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

/**
 * DistributedRedisLockTest
 *
 * @author fantasticmao
 * @since 2020-05-14
 */
public class DistributedRedisLockTest {
    private final int pollSize;
    private final JedisPool jedisPool;
    private final ExecutorService threadPool;

    public DistributedRedisLockTest() {
        this.pollSize = 10;

        GenericObjectPoolConfig<Jedis> poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(pollSize);
        this.jedisPool = new JedisPool(poolConfig, "localhost", 6379);

        // JedisPool 连接池大小需要 >= ThreadPool 线程池大小，避免从 JedisPool 获取连接时阻塞
        this.threadPool = Executors.newFixedThreadPool(pollSize);
    }

    @After
    public void after() {
        this.threadPool.shutdownNow();
        this.jedisPool.close();
    }

    @Test
    public void testRedisLock() throws InterruptedException {
        int repeatTimes = 10;
        AtomicInteger count = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(this.pollSize);

        for (int i = 0; i < this.pollSize; i++) {
            this.threadPool.execute(() -> {
                Lock lock = new DistributedRedisLock(this.jedisPool, "lock");
                lock.lock();
                try {
                    for (int j = 0; j < repeatTimes; j++) {
                        System.out.println(Thread.currentThread().getName() + ": " + count.incrementAndGet());
                    }
                } finally {
                    lock.unlock();
                    latch.countDown();
                }
            });
        }

        latch.await();
        Assert.assertEquals(this.pollSize * repeatTimes, count.get());
    }

}