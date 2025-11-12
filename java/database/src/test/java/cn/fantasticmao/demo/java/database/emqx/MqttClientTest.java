package cn.fantasticmao.demo.java.database.emqx;

import com.hivemq.client.mqtt.datatypes.MqttQos;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * MqttClientTest
 *
 * @author fantasticmao
 * @since 2025-08-14
 */
@Slf4j
public class MqttClientTest {
    final String serverHost = "localhost";
    final int serverPort = 1883;
    final String username = "username";
    final String password = "password";
    final String topicFormat = "sensor/%s/temperature";

    @Test
    public void example() throws InterruptedException {
        try (MqttConsumer consumer = new MqttConsumer("consumer", serverHost, serverPort, username, password)) {
            // MQTT 主题支持以下两种通配符：+ 和 #
            // +：表示单层通配符，例如 a/+ 匹配 a/x 或 a/y
            // #：表示多层通配符，例如 a/# 匹配 a/x、a/b/c/d
            consumer.subscribeAsync(topicFormat.formatted("+"), MqttQos.AT_LEAST_ONCE, message -> {
                log.info("[MQTT Consumer] Received message: {}", new String(message.getPayloadAsBytes()));
            });

            Thread t1 = Thread.startVirtualThread(() -> {
                try (MqttProducer producer = new MqttProducer("producer-1", serverHost, serverPort, username, password)) {
                    for (int i = 0; i < 10; i++) {
                        String payload = "Temperature %d°C".formatted(20 + i);
                        producer.publishSync(topicFormat.formatted("1"), MqttQos.AT_LEAST_ONCE, payload);
                    }
                }
            });

            Thread t2 = Thread.startVirtualThread(() -> {
                try (MqttProducer producer = new MqttProducer("producer-2", serverHost, serverPort, username, password)) {
                    for (int i = 0; i < 10; i++) {
                        String payload = "Temperature %d°C".formatted(10 + i);
                        producer.publishSync(topicFormat.formatted("2"), MqttQos.AT_LEAST_ONCE, payload);
                    }
                }
            });

            t1.join();
            t2.join();
        }
    }

    @Test
    public void subscribe() {
        try (MqttConsumer consumer1 = new MqttConsumer("consumer-1", serverHost, serverPort, username, password);
             MqttConsumer consumer2 = new MqttConsumer("consumer-2", serverHost, serverPort, username, password)) {
            consumer1.subscribeAsync(topicFormat.formatted("+"), MqttQos.AT_LEAST_ONCE, message -> {
                log.info("[MQTT Consumer 1] Received message: {}", new String(message.getPayloadAsBytes()));
            });

            consumer2.subscribeAsync(topicFormat.formatted("+"), MqttQos.AT_LEAST_ONCE, message -> {
                log.info("[MQTT Consumer 2] Received message: {}", new String(message.getPayloadAsBytes()));
            });

            try (MqttProducer producer = new MqttProducer("producer", serverHost, serverPort, username, password)) {
                for (int i = 0; i < 10; i++) {
                    String payload = "Temperature %d°C".formatted(20 + i);
                    producer.publishSync(topicFormat.formatted("1"), MqttQos.AT_LEAST_ONCE, payload);
                }
            }
        }
    }

    @Test
    public void shareSubscribe() throws InterruptedException {
        final String sharePrefix = "$share/defaultGroup/";
        try (MqttConsumer consumer1 = new MqttConsumer("consumer-1", serverHost, serverPort, username, password);
             MqttConsumer consumer2 = new MqttConsumer("consumer-2", serverHost, serverPort, username, password)) {
            consumer1.subscribeAsync(sharePrefix + topicFormat.formatted("+"), MqttQos.AT_LEAST_ONCE, message -> {
                log.info("[MQTT Consumer 1] Received message: {}", new String(message.getPayloadAsBytes()));
            });

            consumer2.subscribeAsync(sharePrefix + topicFormat.formatted("+"), MqttQos.AT_LEAST_ONCE, message -> {
                log.info("[MQTT Consumer 2] Received message: {}", new String(message.getPayloadAsBytes()));
            });

            try (MqttProducer producer = new MqttProducer("producer-1", serverHost, serverPort, username, password)) {
                for (int i = 0; i < 10; i++) {
                    String payload = "Temperature %d°C".formatted(20 + i);
                    producer.publishSync(topicFormat.formatted("1"), MqttQos.AT_LEAST_ONCE, payload);
                }
            }
        }
    }
}
