package cn.fantasticmao.demo.java.database.influxdb;

import com.influxdb.query.FluxRecord;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * InfluxDBRepositoryTest
 *
 * @author fantasticmao
 * @since 2022/1/13
 */
public class InfluxDBRepositoryTest {

    @Test
    public void test() throws InterruptedException {
        final String url = "http://localhost:8086";
        final String token = "MT4RoQE8pwzevlP9YkLhVvJfT6QCHB-m1xfxte5V--G7rbTEjhurwseW1S6iEZiXxjMxHyRJT0T9DrWprQb4Vg==";
        final String org = "fantasticmao.cn";
        final String bucket = "events";
        final Random random = new Random();
        final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        try (InfluxDBRepository repository = new InfluxDBRepository(url, token, org, bucket)) {
            repository.writeByDataPoint("west", random.nextDouble() * 100);
            repository.writeByLineProtocol("north", random.nextDouble() * 100);
            repository.writeByPojo("south", random.nextDouble() * 100);

            List<FluxRecord> recordList = repository.queryData(bucket, "-15m");
            recordList.stream()
                .filter(record -> Objects.nonNull(record.getTime()))
                .filter(record -> Objects.nonNull(record.getValueByKey("location")))
                .filter(record -> Objects.nonNull(record.getValue()))
                .sorted(Comparator.comparing(FluxRecord::getTime))
                .forEach(record -> {
                    ZonedDateTime time = record.getTime().atZone(ZoneId.systemDefault());
                    double value = (Double) record.getValue();
                    System.out.printf("time: %s, location: %s, value: %.1f%n", time.format(timeFormatter),
                        record.getValueByKey("location"), value);
                });
        }
    }

}
