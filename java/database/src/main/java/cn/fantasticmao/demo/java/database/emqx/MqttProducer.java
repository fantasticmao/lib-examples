package cn.fantasticmao.demo.java.database.emqx;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.MqttClientTransportConfig;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import com.hivemq.client.mqtt.mqtt5.message.auth.Mqtt5SimpleAuth;
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5PublishResult;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * MqttProducer
 *
 * @author fantasticmao
 * @since 2025-08-14
 */
@Slf4j
public class MqttProducer implements AutoCloseable {
    private final Mqtt5BlockingClient mqttClient;

    public MqttProducer(String clientId, String serverHost, int serverPort, String username, String password) {
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
                log.info("[MQTT Producer] Connected to server! clientId: {}, serverAddress: {}",
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

    public void publishAsync(String topic, MqttQos qos, String payload) {
        CompletableFuture<Mqtt5PublishResult> resultFuture = mqttClient.toAsync().publishWith()
            .topic(topic)
            .qos(qos)
            .payload(payload.getBytes(StandardCharsets.UTF_8))
            .send();
        resultFuture.whenComplete((result, throwable) -> {
            result.getError().ifPresent(Throwable::printStackTrace);
            if (throwable != null) {
                log.error(throwable.getMessage(), throwable);
            }
        });
    }

    public void publishSync(String topic, MqttQos qos, String payload) {
        Mqtt5PublishResult result = mqttClient.publishWith()
            .topic(topic)
            .qos(qos)
            .payload(payload.getBytes(StandardCharsets.UTF_8))
            .send();
        result.getError().ifPresent(Throwable::printStackTrace);
    }
}
