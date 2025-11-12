package cn.fantasticmao.demo.java.lang.java16;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
        Point point = new Point(1.0, 2.0);
        log.info("Point x: {}", point.x());
        log.info("Point y: {}", point.y());

        //Record classes are immutable
        //point.x = 3.0; // This line would cause a compilation error
    }
}
