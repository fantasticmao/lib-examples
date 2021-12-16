package cn.fantasticmao.demo.java.database.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

/**
 * Idempotent Method
 *
 * @author fantasticmao
 * @since 2020-01-11
 */
public class IdempotentMethod implements AutoCloseable {
    private int count = 0;
    private Jedis jedis;

    public IdempotentMethod() {
        this.jedis = new Jedis("localhost", 6379);
    }

    public int getCount() {
        return count;
    }

    @Override
    public void close() throws Exception {
        jedis.close();
    }

    public boolean idempotentMethod(long uniqueId) {
        final String key = "idempotent-method:" + uniqueId;
        final String value = String.valueOf(uniqueId);
        final String statusCode = jedis.set(key, value, SetParams.setParams().ex(5).nx());
        if ("ok".equalsIgnoreCase(statusCode)) {
            count++;
        }
        return true;
    }

}
