package cn.fantasticmao.demo.java.apache.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * ConsumerPull
 *
 * @author maomao
 * @since 2020-12-14
 */
public class ConsumerPull {
    private static Map<MessageQueue, Long/* offset */> offsetMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(MqConstant.CONSUMER_PULL_GROUP);
        consumer.setNamesrvAddr(MqConstant.NAME_SERVER_ADDRESS);
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.start();
        System.out.println("ConsumerPull Started.");

        // 在分布式环境中，消费者在消费消息时还需要考虑负载均衡的问题
        Set<MessageQueue> queueSet = consumer.fetchSubscribeMessageQueues(MqConstant.TOPIC_DEFAULT);
        while (!Thread.currentThread().isInterrupted()) {
            for (MessageQueue mq : queueSet) {
                long offset = offsetMap.getOrDefault(mq, 0L);
                PullResult pullResult = consumer.pull(mq, "*", offset, 10);
                switch (pullResult.getPullStatus()) {
                    case FOUND:
                        for (MessageExt msg : pullResult.getMsgFoundList()) {
                            System.out.printf("%s Receive New Messages: %s %s %d \"%s\" %n", Thread.currentThread().getName(),
                                msg.getTopic(), msg.getMsgId(), msg.getQueueId(),
                                new String(msg.getBody(), StandardCharsets.UTF_8));
                        }
                        offsetMap.put(mq, pullResult.getNextBeginOffset());
                        break;
                    case NO_NEW_MSG:
                        TimeUnit.MILLISECONDS.sleep(500);
                        break;
                    case NO_MATCHED_MSG:
                    case OFFSET_ILLEGAL:
                        throw new IllegalStateException();
                }
            }
        }
    }
}
