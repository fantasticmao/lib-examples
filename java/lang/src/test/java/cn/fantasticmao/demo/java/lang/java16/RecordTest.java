package cn.fantasticmao.demo.java.lang.java16;

import org.junit.Test;

/**
 * RecordTest
 *
 * @author fantasticmao
 * @since 2025-06-26
 */
public class RecordTest {
    public record Point(double x, double y) {
    }

    @Test
    public void testRecord() {
        Point point = new Point(1.0, 2.0);
        System.out.println("Point x: " + point.x());
        System.out.println("Point y: " + point.y());

        //Record classes are immutable
        //point.x = 3.0; // This line would cause a compilation error
    }
}
