package cn.fantasticmao.demo.java.lang.java16;

import org.junit.Test;

/**
 * RecordTest
 *
 * @author fantasticmao
 * @since 2025-06-26
 */
public class RecordTest {
    public record Coordinate(double x, double y) {
    }

    @Test
    public void testRecord() {
        Coordinate coordinate = new Coordinate(1.0, 2.0);
        System.out.println("Coordinate x: " + coordinate.x());
        System.out.println("Coordinate y: " + coordinate.y());

        // Record classes are immutable
        // coordinate.x = 3.0; // This line would cause a compilation error
    }
}
