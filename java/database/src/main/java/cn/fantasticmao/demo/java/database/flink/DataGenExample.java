package cn.fantasticmao.demo.java.database.flink;

import cn.fantasticmao.demo.java.database.User;
import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * DataGenExample
 *
 * @author fantasticmao
 * @see <a href="https://nightlies.apache.org/flink/flink-docs-release-1.17/docs/connectors/datastream/datagen/">DataGen Connector</a>
 * @since 2023-06-19
 */
public class DataGenExample {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().setClosureCleanerLevel(ExecutionConfig.ClosureCleanerLevel.NONE);

        DataStream<User> input = env.fromElements(
                new User(1, "Tom", 20),
                new User(2, "Bob", 17),
                new User(3, "Anni", 18)
            )
            .name("Generator Source");

        DataStream<User> adults = input.filter(new FilterFunction<User>() {
                @Override
                public boolean filter(User user) throws Exception {
                    return user.getAge() >= 18;
                }
            })
            .name("Filter out Adults");

        adults.print();

        env.execute();
    }
}
