package cn.fantasticmao.demo.java.database.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

/**
 * DistributedRedisLockTest
 *
 * @author fantasticmao
 * @since 2020-05-14
 */
public class DistributedRedisLockTest {
    private int pollSize;

    // ① JedisPool 连接池大小需 >= ② ThreadPool 线程池大小，为了避免程序在向 JedisPool 获取连接时阻塞
    private JedisPool jedisPool;
    private ExecutorService executorService;

    @Before
    public void before() {
        this.pollSize = 10;

        GenericObjectPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(pollSize); // ① 设置 JedisPool 连接池大小
        this.jedisPool = new JedisPool(poolConfig, "localhost", 6379);

        this.executorService = Executors.newFixedThreadPool(pollSize); // ② 设置 ThreadPool 线程池大小
    }

    @After
    public void after() throws InterruptedException {
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        jedisPool.close();
    }

    @Test
    @Ignore
    public void testRedisLock() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < pollSize; i++) {
            executorService.execute(() -> {
                Lock lock = new DistributedRedisLock(jedisPool, "lock");
                lock.lock();
                try {
                    for (int j = 0; j < 10; j++) {
                        System.out.println(Thread.currentThread().getName() + ": " + atomicInteger.incrementAndGet());
                    }
                    System.out.println();
                } finally {
                    lock.unlock();
                }
            });
        }
    }

}