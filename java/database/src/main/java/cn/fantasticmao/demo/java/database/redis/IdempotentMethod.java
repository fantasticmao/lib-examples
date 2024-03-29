package cn.fantasticmao.demo.java.database.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

/**
 * Idempotent Method
 * <p>
 * 启动 Redis Docker 容器
 *
 * @author fantasticmao
 * @since 2020-01-11
 */
public class IdempotentMethod {
    private final Jedis jedis;

    public IdempotentMethod(Jedis jedis) {
        this.jedis = jedis;
    }

    public void idempotentMethod(String uniqueKey, Runnable runnable) {
        final String key = "idempotent:" + uniqueKey;
        final String value = String.valueOf(uniqueKey);
        final String statusCode = jedis.set(key, value, SetParams.setParams().ex(5L).nx());
        if ("ok".equalsIgnoreCase(statusCode)) {
            runnable.run();
        }
    }

}
