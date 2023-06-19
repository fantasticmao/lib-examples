package cn.fantasticmao.demo.java.database.flink;

import cn.fantasticmao.demo.java.database.User;
import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.connector.file.src.reader.TextLineInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.net.URL;

/**
 * FileSystemExample
 *
 * @author fantasticmao
 * @see <a href="https://nightlies.apache.org/flink/flink-docs-release-1.17/docs/connectors/datastream/filesystem/">FileSystem</a>
 * @since 2023-06-20
 */
public class FileSystemExample {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().setClosureCleanerLevel(ExecutionConfig.ClosureCleanerLevel.NONE);

        URL url = FileSystemExample.class.getClassLoader().getResource("user.csv");
        assert url != null;
        FileSource<String> source = FileSource
            .forRecordStreamFormat(new TextLineInputFormat(), new Path(url.toURI()))
            //.monitorContinuously(Duration.ofSeconds(3))
            .build();

        DataStream<String> input = env.fromSource(source,
            WatermarkStrategy.noWatermarks(), "File Source");

        DataStream<User> user = input.map(new MapFunction<String, User>() {
                @Override
                public User map(String line) throws Exception {
                    String[] elements = line.split(",");
                    Integer id = Integer.valueOf(elements[0]);
                    String name = elements[1];
                    Integer age = Integer.valueOf(elements[2]);
                    String email = elements[3];
                    return new User(id, name, age, email);
                }
            })
            .name("Map to User");

        user.print();

        env.execute();
    }
}
