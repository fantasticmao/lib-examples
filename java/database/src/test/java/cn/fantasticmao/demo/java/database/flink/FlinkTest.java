package cn.fantasticmao.demo.java.database.flink;

import cn.fantasticmao.demo.java.database.User;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.connector.file.src.reader.TextLineInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

/**
 * FlinkTest
 *
 * @author fantasticmao
 * @see <a href="https://nightlies.apache.org/flink/flink-docs-release-1.17/docs/dev/datastream/overview/">Flink DataStream API Programming Guide</a>
 * @see <a href="https://nightlies.apache.org/flink/flink-docs-release-1.17/docs/dev/datastream/event-time/generating_watermarks/">Generating Watermarks</a>
 * @see <a href="https://nightlies.apache.org/flink/flink-docs-release-1.17/docs/dev/datastream/fault-tolerance/state/">Working with State</a>
 * @see <a href="https://nightlies.apache.org/flink/flink-docs-release-1.17/docs/dev/datastream/user_defined_functions/">User-Defined Functions</a>
 * @see <a href="https://nightlies.apache.org/flink/flink-docs-release-1.17/docs/dev/datastream/operators/overview/">Operators</a>
 * @see <a href="https://nightlies.apache.org/flink/flink-docs-release-1.17/docs/connectors/datastream/filesystem/">FileSystem Connector</a>
 * @see <a href="https://nightlies.apache.org/flink/flink-docs-release-1.17/docs/connectors/datastream/kafka/">Apache Kafka Connector</a>
 * @since 2023-06-29
 */
public class FlinkTest {

    private final StreamExecutionEnvironment env;

    public FlinkTest() {
        env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().registerPojoType(User.class);
    }

    @Test
    public void fileText() throws Exception {
        URL url = getClass().getClassLoader().getResource("user.csv");
        Assert.assertNotNull(url);

        FileSource<String> source = FileSource
            .forRecordStreamFormat(new TextLineInputFormat(), new Path(url.toURI()))
            //.monitorContinuously(Duration.ofSeconds(3))
            .build();
        DataStream<String> input = env.fromSource(source, WatermarkStrategy.noWatermarks(), "File Source");

        DataStream<User> users = input
            .map(new UserMapFunction())
            .name("Convert to User");
        DataStream<User> adults = users
            .filter(new UserAgeFilterFunction(18))
            .name("Filter out Adults");

        UserLogSinkFunction userLogSink = new UserLogSinkFunction();
        adults.addSink(userLogSink);
        UserCollectSinkFunction userCollectSink = new UserCollectSinkFunction();
        adults.addSink(userCollectSink);

        env.execute();

        Assert.assertEquals(2, userLogSink.count().intValue());
        Assert.assertEquals(2, userCollectSink.length());
    }

    @Test
    public void socketStream() throws Exception {
        DataStream<String> input = env.socketTextStream("localhost", 5678);

        DataStream<User> users = input
            .map(new UserMapFunction())
            .name("Convert to User");

        UserLogSinkFunction userLogSink = new UserLogSinkFunction();
        users.addSink(userLogSink);
        users.print();

        env.execute();

        Assert.assertEquals(3, userLogSink.count().intValue());
    }
}
