package cn.fantasticmao.demo.java.database.influxdb;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import java.time.Instant;

/**
 * Temperature
 *
 * @author fantasticmao
 * @since 2022/1/12
 */
@Measurement(name = "temperature")
public class Temperature {
    @Column(tag = true)
    private String location;

    @Column
    private Double value;

    @Column(timestamp = true)
    private Instant time;

    public Temperature() {
    }

    public Temperature(String location, Double value, Instant time) {
        this.location = location;
        this.value = value;
        this.time = time;
    }

    // getter and setter

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }
}
