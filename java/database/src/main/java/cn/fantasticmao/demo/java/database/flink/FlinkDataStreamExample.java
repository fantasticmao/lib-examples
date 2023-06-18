package cn.fantasticmao.demo.java.database.flink;

import cn.fantasticmao.demo.java.database.User;
import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * FlinkDataStreamExample
 *
 * @author fantasticmao
 * @since 2023-06-19
 */
public class FlinkDataStreamExample {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().setClosureCleanerLevel(ExecutionConfig.ClosureCleanerLevel.NONE);

        DataStreamSource<User> users = env.fromElements(
            new User(1, "Tom", 20),
            new User(2, "Bob", 17),
            new User(3, "Anni", 18)
        );
        DataStream<User> adults = users.filter(new FilterFunction<User>() {
            @Override
            public boolean filter(User user) throws Exception {
                return user.getAge() >= 18;
            }
        });

        adults.print();

        env.execute();
    }
}
