package cn.fantasticmao.demo.java.database.emqx;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.function.Consumer;

/**
 * MqttConsumer
 *
 * @author fantasticmao
 * @since 2025-08-14
 */
public class MqttConsumer implements AutoCloseable {
    private final MqttClient mqttClient;

    public MqttConsumer(String clientId, String brokerUrl, String username, String password) throws MqttException {
        this.mqttClient = new MqttClient(brokerUrl, clientId, new MemoryPersistence());

        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setUserName(username);
        connOpts.setPassword(password.toCharArray());
        connOpts.setCleanSession(true);

        System.out.println("[MQTT Consumer] Connecting to broker: " + brokerUrl);
        this.mqttClient.connect(connOpts);
        System.out.println("[MQTT Consumer] Connected to broker: " + brokerUrl);
    }

    @Override
    public void close() throws MqttException {
        if (this.mqttClient.isConnected()) {
            this.mqttClient.disconnect();
            this.mqttClient.close();
        }
    }

    public void subscribe(String topic, Consumer<MqttMessage> handler) throws MqttException {
        this.mqttClient.subscribe(topic, (_topic, message) -> {
            handler.accept(message);
        });
        System.out.println("[MQTT Consumer] Subscribed to topic: " + topic);
    }
}
