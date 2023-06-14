package cn.fantasticmao.demo.java.database.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * MessageProducer
 * <p>
 * 启动 Kafka Docker 容器
 *
 * @author fantasticmao
 * @see <a href="https://developer.confluent.io/get-started/java/#build-producer">Build Producer</a>
 * @since 2023-06-10
 */
public class MessageProducer {

    public static void main(String[] args) {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // see https://kafka.apache.org/documentation/#producerconfigs_max.block.ms
        configs.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 3_000);
        // see https://kafka.apache.org/documentation/#producerconfigs_delivery.timeout.ms
        configs.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, 10_000);
        // see https://kafka.apache.org/documentation/#producerconfigs_request.timeout.ms
        configs.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 5_000);
        // see https://kafka.apache.org/documentation/#producerconfigs_linger.ms
        configs.put(ProducerConfig.LINGER_MS_CONFIG, 3_000);
        // see https://kafka.apache.org/documentation/#producerconfigs_batch.size
        configs.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(configs)) {
            ProducerRecord<String, String> record = new ProducerRecord<>(
                KafkaConstant.TOPIC, "hello world!");

            long startTime = System.nanoTime();
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                }
                System.out.printf("send msg to Kafka success, topic: %s, partition: %s, offset: %s%n",
                    metadata.topic(), metadata.partition(), metadata.offset());
            });
            System.out.printf("time elapsed: %dms%n", TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime));
        }
    }
}
