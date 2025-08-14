package cn.fantasticmao.demo.java.database.emqx;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * MqttClientTest
 *
 * @author fantasticmao
 * @since 2025-08-14
 */
public class MqttClientTest {

    @Test
    public void example() throws MqttException, InterruptedException {
        final String brokerUrl = "tcp://localhost:1883";
        final String username = "username";
        final String password = "password";
        final String topicFormat = "sensor/%s/temperature";

        try (MqttConsumer consumer = new MqttConsumer("consumer-client-1", brokerUrl, username, password)) {
            // MQTT 主题支持以下两种通配符：+ 和 #
            // +：表示单层通配符，例如 a/+ 匹配 a/x 或 a/y
            // #：表示多层通配符，例如 a/# 匹配 a/x、a/b/c/d
            consumer.subscribe(topicFormat.formatted("+"), message -> {
                System.out.printf("Received id: %d, message: %s\n", message.getId(), new String(message.getPayload()));
            });

            final int size = 10;
            final CountDownLatch count = new CountDownLatch(2 * size);

            Thread.startVirtualThread(() -> {
                try (MqttProducer producer = new MqttProducer("producer-client-1", brokerUrl, username, password)) {
                    for (int i = 0; i < size; i++) {
                        String payload = "Temperature %d°C".formatted(20 + i);
                        producer.publish(topicFormat.formatted("1"), payload);
                        count.countDown();
                    }
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            });

            Thread.startVirtualThread(() -> {
                try (MqttProducer producer = new MqttProducer("producer-client-2", brokerUrl, username, password)) {
                    for (int i = 0; i < size; i++) {
                        String payload = "Temperature %d°C".formatted(10 + i);
                        producer.publish(topicFormat.formatted("2"), payload);
                        count.countDown();
                    }
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            });

            count.await();
        }
    }
}
