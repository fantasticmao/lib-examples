package cn.fantasticmao.demo.java.apache.rocketmq;

/**
 * MqConstant
 *
 * @author maomao
 * @since 2020-11-10
 */
public interface MqConstant {
    String NAME_SERVER_ADDRESS = "localhost:9876";

    // 同步消息、异步消息、单向消息

    String TOPIC_DEFAULT = "topic_default";

    String PRODUCER_SYNC_GROUP = "producer_group_sync";

    String PRODUCER_ASYNC_GROUP = "producer_group_async";

    String PRODUCER_ONEWAY_GROUP = "producer_group_oneway";

    String CONSUMER_DEFAULT_GROUP = "consumer_group_default";

    // 顺序消息

    String TOPIC_ORDERED = "topic_ordered";

    String PRODUCER_ORDERED_GROUP = "producer_group_ordered";

    String CONSUMER_ORDERED_GROUP = "consumer_group_ordered";
}
