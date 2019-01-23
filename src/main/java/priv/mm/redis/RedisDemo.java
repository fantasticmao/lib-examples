package priv.mm.redis;

import redis.clients.jedis.Jedis;

/**
 * RedisDemo
 *
 * @author maodh
 * @since 2019/1/23
 */
public class RedisDemo {

    public static void main(String[] args) {
        try (Jedis jedis = new Jedis("localhost", 6379)) {
            String value = jedis.get("key");
            System.out.println(value);
        }
    }
}
