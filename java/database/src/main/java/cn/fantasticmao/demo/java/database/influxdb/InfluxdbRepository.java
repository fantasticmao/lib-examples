package cn.fantasticmao.demo.java.database.influxdb;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

import java.time.Instant;
import java.util.List;

/**
 * InfluxdbRepository
 * <p>
 * <ol>
 *     <li>Run InfluxDB Docker Container: {@code docker run -d -p 8086:8086 --rm --name influxdb-test influxdb:2.1.1}</li>
 *     <li>Set up InfluxDB, username: {@code username}</li>
 *     <li>Set up InfluxDB, password: {@code password}</li>
 *     <li>Set up InfluxDB, organization name: {@code fantasticmao.cn}</li>
 *     <li>Set up InfluxDB, bucket name: {@code events}</li>
 *     <li>Get API Token: {@code token}</li>
 * </ol>
 *
 * @author fantasticmao
 * @see <a href="https://docs.influxdata.com/influxdb/v2.1/install/?t=Docker">Install InfluxDB</a>
 * @see <a href="https://docs.influxdata.com/influxdb/v2.1/write-data/">Write data to InfluxDB</a>
 * @see <a href="https://docs.influxdata.com/influxdb/v2.1/query-data/">Query data in InfluxDB</a>
 * @see <a href="https://docs.influxdata.com/flux/v0.x/get-started/">Get started with Flux</a>
 * @since 2022/1/12
 */
public class InfluxdbRepository implements AutoCloseable {
    private final InfluxDBClient client;
    private final WriteApiBlocking writeApi;
    private final QueryApi queryApi;
    private final String measurement;

    public InfluxdbRepository(String url, String token, String org, String bucket) {
        this.client = InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
        this.writeApi = this.client.getWriteApiBlocking();
        this.queryApi = this.client.getQueryApi();
        this.measurement = "temperature";
    }

    public void writeByDataPoint(String location, double value) {
        Point point = Point.measurement(this.measurement)
            .addTag("location", location)
            .addField("value", value)
            .time(Instant.now().toEpochMilli(), WritePrecision.MS);
        this.writeApi.writePoint(point);
    }

    public void writeByLineProtocol(String location, double value) {
        this.writeApi.writeRecord(WritePrecision.NS, String.format("%s,location=%s value=%.1f",
            this.measurement, location, value));
    }

    public void writeByPojo(String location, double value) {
        Temperature temperature = new Temperature(location, value, Instant.now());
        this.writeApi.writeMeasurement(WritePrecision.NS, temperature);
    }

    public List<FluxRecord> queryData(String bucket, String start) {
        List<FluxTable> tableList = this.queryApi.query(String.format("from(bucket:\"%s\") |> range(start: %s)",
            bucket, start));
        return tableList.stream()
            .flatMap(table -> table.getRecords().stream())
            .toList();
    }

    @Override
    public void close() {
        if (this.client != null) {
            this.client.close();
        }
    }
}
