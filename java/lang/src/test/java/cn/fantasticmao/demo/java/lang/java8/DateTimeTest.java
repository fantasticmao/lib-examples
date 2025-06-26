package cn.fantasticmao.demo.java.lang.java8;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

/**
 * DateTimeTest
 *
 * @author fantasticmao
 * @since 2018-03-11
 */
public class DateTimeTest {

    @Test
    public void plusDay() {
        LocalDate start = LocalDate.of(2018, 2, 20);
        LocalDate end = start.plusDays(1000);
        Assert.assertEquals(2020, end.getYear());
        Assert.assertEquals(11, end.getMonthValue());
        Assert.assertEquals(16, end.getDayOfMonth());
    }
}
