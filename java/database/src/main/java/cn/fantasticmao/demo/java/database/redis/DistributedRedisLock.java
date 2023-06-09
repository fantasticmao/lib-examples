package cn.fantasticmao.demo.java.database.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * DistributedRedisLock 单机 Redis 的分布式锁实现
 * <p>
 * 启动 Redis Docker 容器
 *
 * @author fantasticmao
 * @see <a href="https://redis.io/topics/distlock">Distributed locks with Redis</a>
 * @since 2019/1/23
 */
public class DistributedRedisLock implements Lock {
    private final JedisPool jedisPool;
    private final String lockKey;
    private final String randomString;
    private final long millisecondsToExpire;

    public DistributedRedisLock(JedisPool jedisPool, String key) {
        this(jedisPool, key, TimeUnit.SECONDS, 10);
    }

    public DistributedRedisLock(JedisPool jedisPool, String key, TimeUnit unit, long duration) {
        this.jedisPool = jedisPool; // 使用 JedisPool 获取线程安全的 Redis 连接
        this.lockKey = key;
        this.randomString = UUID.randomUUID().toString();
        this.millisecondsToExpire = unit.toMillis(duration);
    }

    @Override
    public void lock() {
        try (Jedis jedis = jedisPool.getResource()) {
            while (true) {
                String result = jedis.set(lockKey, randomString, SetParams.setParams().px(millisecondsToExpire).nx());
                if (result != null) {
                    break;
                } else {
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException e) {
                        // ignore
                    }
                }
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        try (Jedis jedis = jedisPool.getResource()) {
            while (!Thread.currentThread().isInterrupted()) {
                String result = jedis.set(lockKey, randomString, SetParams.setParams().px(millisecondsToExpire).nx());
                if (result != null) {
                    break;
                } else {
                    TimeUnit.MILLISECONDS.sleep(50);
                }
            }
        }
    }

    @Override
    public boolean tryLock() {
        try (Jedis jedis = jedisPool.getResource()) {
            String result = jedis.set(lockKey, randomString, SetParams.setParams().px(millisecondsToExpire).nx());
            return result != null;
        }
    }

    @Override
    public boolean tryLock(long time, @Nonnull TimeUnit unit) throws InterruptedException {
        try (Jedis jedis = jedisPool.getResource()) {
            long startTime = System.nanoTime();
            long expireTime = startTime + unit.toNanos(time);
            while (!Thread.currentThread().isInterrupted()) {
                String result = jedis.set(lockKey, randomString, SetParams.setParams().px(millisecondsToExpire).nx());
                if (result != null) {
                    return true;
                } else if (System.nanoTime() >= expireTime) {
                    return false;
                } else {
                    TimeUnit.MILLISECONDS.sleep(50);
                }
            }
            return false;
        }
    }

    @Override
    public void unlock() {
        try (Jedis jedis = jedisPool.getResource()) {
            final String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then return redis.call(\"del\",KEYS[1]) else return 0 end";
            jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(randomString));
        }
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }
}
