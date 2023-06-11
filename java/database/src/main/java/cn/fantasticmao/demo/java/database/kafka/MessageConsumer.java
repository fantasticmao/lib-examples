package cn.fantasticmao.demo.java.database.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MessageConsumer
 * <p>
 * 启动 Kafka Docker 容器
 *
 * @author fantasticmao
 * @see <a href="https://developer.confluent.io/get-started/java/#build-consumer">Build Consumer</a>
 * @since 2023-06-10
 */
public class MessageConsumer {

    public static void main(String[] args) {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.BOOTSTRAP_SERVERS);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstant.CONSUMER);
        // see https://kafka.apache.org/documentation/#consumerconfigs_auto.offset.reset
        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OffsetResetStrategy.EARLIEST.toString());
        // see https://kafka.apache.org/documentation/#consumerconfigs_partition.assignment.strategy
        configs.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, Collections.singletonList(RoundRobinAssignor.class));
        // see https://kafka.apache.org/documentation/#consumerconfigs_request.timeout.ms
        configs.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 5_000);

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configs)) {
            List<PartitionInfo> partitionInfos = consumer.partitionsFor(KafkaConstant.TOPIC);
            System.out.printf("partitions for %s: %s%n", KafkaConstant.TOPIC, partitionInfos);

            consumer.subscribe(Collections.singletonList(KafkaConstant.TOPIC));
            for (; ; ) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10_000));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("record: %s%n", record);
                }
            }
        }
    }
}
