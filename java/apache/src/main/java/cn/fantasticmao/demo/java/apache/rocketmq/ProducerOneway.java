package cn.fantasticmao.demo.java.apache.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * ProducerOneway
 *
 * @author maomao
 * @since 2020-11-10
 */
public class ProducerOneway {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(MqConstant.PRODUCER_ONEWAY_GROUP);
        producer.setNamesrvAddr(MqConstant.NAME_SERVER_ADDRESS);

        producer.start();
        try {
            for (int i = 0; i < 10; i++) {
                Message message = new Message(MqConstant.TOPIC_DEFAULT, "tag1",
                    ("Hello MQ Oneway " + i).getBytes(StandardCharsets.UTF_8));
                // sendOneway 方法返回 void，所发送的消息是不可靠的
                producer.sendOneway(message);
            }
        } finally {
            producer.shutdown();
        }
    }
}
