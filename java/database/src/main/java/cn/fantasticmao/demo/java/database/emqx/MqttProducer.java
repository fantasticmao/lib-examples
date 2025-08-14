package cn.fantasticmao.demo.java.database.emqx;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;

/**
 * MqttProducer
 *
 * @author fantasticmao
 * @since 2025-08-14
 */
public class MqttProducer implements AutoCloseable {
    private final MqttClient mqttClient;

    public MqttProducer(String clientId, String brokerUrl, String username, String password) throws MqttException {
        this.mqttClient = new MqttClient(brokerUrl, clientId, new MemoryPersistence());

        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setUserName(username);
        connOpts.setPassword(password.toCharArray());
        connOpts.setCleanSession(true);

        System.out.println("[MQTT Producer] Connecting to broker: " + brokerUrl);
        this.mqttClient.connect(connOpts);
        System.out.println("[MQTT Producer] Connected to broker: " + brokerUrl);
    }

    @Override
    public void close() throws MqttException {
        if (this.mqttClient.isConnected()) {
            this.mqttClient.disconnect();
            this.mqttClient.close();
        }
    }

    public void publish(String topic, String payload) throws MqttException {
        MqttMessage message = new MqttMessage(payload.getBytes(StandardCharsets.UTF_8));
        message.setRetained(false);
        this.mqttClient.publish(topic, message);
    }
}
