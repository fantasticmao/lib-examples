package cn.fantasticmao.demo.java.apache.rocketmq.orderedmsg;

import cn.fantasticmao.demo.java.apache.rocketmq.MqConstant;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * OrderedProducer
 *
 * @author fantasticmao
 * @since 2020-11-10
 */
public class OrderedProducer {

    public static List<OrderStep> newOrderStep() {
        long orderId1 = 19950815;
        long orderId2 = 19950816;
        long orderId3 = 19950817;
        return Arrays.asList(
            new OrderStep(orderId1, OrderStep.DESC_CREATE),
            new OrderStep(orderId2, OrderStep.DESC_CREATE),
            new OrderStep(orderId1, OrderStep.DESC_PAID),
            new OrderStep(orderId3, OrderStep.DESC_CREATE),
            new OrderStep(orderId1, OrderStep.DESC_FINISH),
            new OrderStep(orderId3, OrderStep.DESC_PAID),
            new OrderStep(orderId2, OrderStep.DESC_PAID),
            new OrderStep(orderId2, OrderStep.DESC_FINISH),
            new OrderStep(orderId3, OrderStep.DESC_FINISH)
        );
    }

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(MqConstant.PRODUCER_ORDERED_GROUP);
        producer.setNamesrvAddr(MqConstant.NAME_SERVER_ADDRESS);

        producer.start();
        try {
            List<OrderStep> orderStepList = newOrderStep();
            for (OrderStep orderStep : orderStepList) {
                Message message = new Message(MqConstant.TOPIC_ORDERED, "tag1",
                    String.format("Hello MQ Ordered Msg %d %s", orderStep.orderId, orderStep.stepDesc)
                        .getBytes(StandardCharsets.UTF_8));
                SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        // 根据 orderId % queueSize 来选择对应的 MessageQueue
                        // 保证同一个 orderId 的消息只会发送到同一个 MessageQueue 中
                        Long orderId = (Long) arg;
                        long index = orderId % mqs.size();
                        return mqs.get((int) index);
                    }
                }, orderStep.orderId);
                System.out.printf("%s %s %s %n", sendResult.getSendStatus(),
                    sendResult.getMessageQueue().getTopic(), sendResult.getMsgId());
            }
        } finally {
            producer.shutdown();
        }
    }
}
