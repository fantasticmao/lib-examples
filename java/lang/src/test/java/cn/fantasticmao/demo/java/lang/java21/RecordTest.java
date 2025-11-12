package cn.fantasticmao.demo.java.lang.java21;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

/**
 * RecordTest
 *
 * @author fantasticmao
 * @since 2025-06-26
 */
@Slf4j
public class RecordTest {
    public record Point(double x, double y) {
    }

    @Test
    public void testRecord() {
        List<Object> list = List.of(
            new Point(1.0, 2.0),
            new Point(3.0, 4.0)
        );

        for (Object obj : list) {
            if (obj instanceof Point(double x, double y)) {
                log.info("Point: x={}, y={}", x, y);
            }
        }
    }
}
