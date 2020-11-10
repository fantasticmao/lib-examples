package cn.fantasticmao.demo.java.apache.rocketmq;

/**
 * MqConstant
 *
 * @author maomao
 * @since 2020-11-10
 */
public interface MqConstant {
    String NAME_SERVER_ADDRESS = "localhost:9876";

    String TOPIC = "demo_topic";

    String PRODUCER_SYNC_GROUP = "producer_group_sync";

    String PRODUCER_ASYNC_GROUP = "producer_group_async";

    String PRODUCER_ONEWAY_GROUP = "producer_group_oneway";

    String CONSUMER_GROUP_DEFAULT = "consumer_group_default";
}
