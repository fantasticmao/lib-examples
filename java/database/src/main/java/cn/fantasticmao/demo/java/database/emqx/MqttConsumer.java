package cn.fantasticmao.demo.java.database.emqx;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.MqttClientTransportConfig;
import com.hivemq.client.mqtt.MqttGlobalPublishFilter;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import com.hivemq.client.mqtt.mqtt5.message.auth.Mqtt5SimpleAuth;
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish;
import com.hivemq.client.mqtt.mqtt5.message.subscribe.suback.Mqtt5SubAck;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * MqttConsumer
 *
 * @author fantasticmao
 * @since 2025-08-14
 */
public class MqttConsumer implements AutoCloseable {
    private final Mqtt5BlockingClient mqttClient;

    public MqttConsumer(String clientId, String serverHost, int serverPort, String username, String password) {
        Mqtt5BlockingClient client = MqttClient.builder()
            .useMqttVersion5()
            .identifier(clientId)
            .simpleAuth(Mqtt5SimpleAuth.builder()
                .username(username)
                .password(password.getBytes())
                .build()
            )
            .transportConfig(MqttClientTransportConfig.builder()
                .serverHost(serverHost)
                .serverPort(serverPort)
                .mqttConnectTimeout(500, TimeUnit.MICROSECONDS)
                .socketConnectTimeout(500, TimeUnit.MICROSECONDS)
                .build())
            .automaticReconnectWithDefaultConfig()
            .addConnectedListener(context ->
                System.out.printf("[MQTT Consumer] Connected to server! clientId: %s, serverAddress: %s%n",
                    context.getClientConfig().getClientIdentifier(),
                    context.getClientConfig().getServerAddress())
            )
            .buildBlocking();

        client.connectWith()
            .cleanStart(true)
            .keepAlive(10)
            .send();

        this.mqttClient = client;
    }

    @Override
    public void close() {
        if (this.mqttClient != null && this.mqttClient.getState().isConnected()) {
            this.mqttClient.disconnect();
        }
    }

    public void subscribeAsync(String topic, MqttQos qos, Consumer<Mqtt5Publish> callback) {
        CompletableFuture<Mqtt5SubAck> ackFuture = this.mqttClient.toAsync().subscribeWith()
            .topicFilter(topic)
            .qos(qos)
            .callback(callback)
            .send();
        ackFuture.whenComplete((subAck, throwable) -> {
            if (subAck != null && throwable == null) {
                System.out.println("[MQTT Consumer] Subscribed to topic: " + topic);
            } else {
                throwable.printStackTrace();
            }
        });
    }

    public void subscribeSync(String topic, MqttQos qos, Consumer<Mqtt5Publish> callback) throws InterruptedException {
        Mqtt5SubAck subAck = this.mqttClient.subscribeWith()
            .topicFilter(topic)
            .qos(qos)
            .send();
        System.out.println("[MQTT Consumer] Subscribed to topic: " + topic);

        try (Mqtt5BlockingClient.Mqtt5Publishes accepter = this.mqttClient.publishes(MqttGlobalPublishFilter.SUBSCRIBED)) {
            while (!Thread.interrupted()) {
                Mqtt5Publish msg = accepter.receive();
                callback.accept(msg);
            }
        }
    }
}
