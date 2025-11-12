package cn.fantasticmao.demo.java.database.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.apache.kafka.clients.consumer.RoundRobinAssignor;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MessageConsumer_1
 * <p>
 * 启动 Kafka Docker 容器
 *
 * @author fantasticmao
 * @see <a href="https://developer.confluent.io/get-started/java/#build-consumer">Build Consumer</a>
 * @since 2023-06-10
 */
@Slf4j
public class MessageConsumer_1 {

    public static void main(String[] args) {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.BOOTSTRAP_SERVERS);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstant.CONSUMER);
        configs.put(ConsumerConfig.CLIENT_ID_CONFIG, KafkaConstant.CONSUMER + "_1");
        // see https://kafka.apache.org/documentation/#consumerconfigs_auto.offset.reset
        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OffsetResetStrategy.EARLIEST.toString());
        // see https://kafka.apache.org/documentation/#consumerconfigs_partition.assignment.strategy
        configs.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, Collections.singletonList(RoundRobinAssignor.class));
        // see https://kafka.apache.org/documentation/#consumerconfigs_request.timeout.ms
        configs.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 5_000);

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configs)) {
            List<PartitionInfo> partitionInfos = consumer.partitionsFor(KafkaConstant.TOPIC);
            log.info("partitions for {}: {}", KafkaConstant.TOPIC, partitionInfos);

            consumer.subscribe(Collections.singletonList(KafkaConstant.TOPIC), new ConsumerRebalanceListener() {
                @Override
                public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                    log.info("{} partitions revoked: {}", LocalDateTime.now(), partitions);
                }

                @Override
                public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                    log.info("{} partitions assigned: {}", LocalDateTime.now(), partitions);
                }
            });
            for (; ; ) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10_000));
                for (ConsumerRecord<String, String> record : records) {
                    log.info("record: {}", record);
                }
            }
        }
    }
}
