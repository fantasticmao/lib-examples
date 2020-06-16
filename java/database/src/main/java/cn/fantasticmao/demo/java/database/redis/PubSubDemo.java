package cn.fantasticmao.demo.java.database.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * PubSubDemo
 *
 * @author maomao
 * @since 2020-06-16
 */
public class PubSubDemo extends JedisPubSub {

    static class Subscriber extends JedisPubSub {

        @Override
        public void onMessage(String channel, String message) {
            System.out.printf("channel: %s, message: %s%n", channel, message);
        }

        @Override
        public void onSubscribe(String channel, int subscribedChannels) {
            System.out.printf("subscribe channel: %s%n", channel);
        }

        @Override
        public void onUnsubscribe(String channel, int subscribedChannels) {
            System.out.printf("unsubscribe channel: %s%n", channel);
        }
    }

    public static void main(String[] args) {
        final JedisPool jedisPool = new JedisPool("localhost", 6379);
        final Subscriber subscriber = new Subscriber();
        final String channel = "channel_test";
        final AtomicBoolean isSubscribed = new AtomicBoolean(false);

        // subscribe message from channel
        new Thread(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.subscribe(subscriber, channel);
                isSubscribed.set(true);
            }
        }, "PubSubDemo").start();

        // publish message to channel
        try (Jedis jedis = jedisPool.getResource()) {
            for (int i = 0; i < 10; i++) {
                jedis.publish(channel, "count: " + i);
            }
        }

        // unsubscribe
        for (; ; ) {
            if (!isSubscribed.get()) {
                subscriber.unsubscribe(channel);
                break;
            }
        }
    }

}
