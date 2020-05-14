package cn.fantasticmao.demo.java.lang.java8;

import java.time.*;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

/**
 * DateTimeDemo
 *
 * @author maomao
 * @since 2016.11.20
 */
public class DateTimeDemo {

    static void demo() {
        LocalDateTime timePoint = LocalDateTime.now(); // The current date and time
        LocalDate.of(2012, Month.DECEMBER, 12); // from values
        LocalDate.ofEpochDay(150); // middle of 1970
        LocalTime.of(17, 18); // the train I took home today
        LocalTime.parse("10:15:30"); // From a String


        LocalDate theDate = timePoint.toLocalDate();
        Month month = timePoint.getMonth();
        int day = timePoint.getDayOfMonth();
        timePoint.getSecond();


        // Set the value, returning a new object
        LocalDateTime thePast = timePoint.withDayOfMonth(10).withYear(2010);
        // You can use direct manipulation methods, or pass a value and field pair
        LocalDateTime yetAnother = thePast.plusWeeks(3).plus(3, ChronoUnit.WEEKS);


        LocalDateTime foo = timePoint.with(TemporalAdjusters.lastDayOfMonth());
        LocalDateTime bar = timePoint.with(TemporalAdjusters.previousOrSame(DayOfWeek.WEDNESDAY));
        // Using value classes as adjusters
        timePoint.with(LocalTime.now());


        LocalTime truncatedTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);


        // You can specify the zone id when creating a zoned date time
        ZoneId id = ZoneId.of("Europe/Paris");
        ZonedDateTime zoned = ZonedDateTime.of(LocalDateTime.now(), id);
        assert Objects.equals(id, ZoneId.from(zoned));


        OffsetTime time = OffsetTime.now();
        // changes offset, while keeping the same point on the timeline
        OffsetTime sameTimeDifferentOffset = time.withOffsetSameInstant(ZoneOffset.UTC);
        // changes the offset, and updates the point on the timeline
        OffsetTime changeTimeWithNewOffset = time.withOffsetSameLocal(ZoneOffset.ofTotalSeconds(8));
        // Can also create new object with altered fields as before
        changeTimeWithNewOffset.withHour(3).plusSeconds(2);


        // 3 years, 2 months, 1 day
        Period period = Period.of(3, 2, 1);
        // You can modify the values of dates using periods
        LocalDate newDate = LocalDate.now().plus(period);
        ZonedDateTime newDateTime = ZonedDateTime.now().minus(period);
        // Components of a Period are represented by ChronoUnit values
        assert Objects.equals(1, period.get(ChronoUnit.DAYS));


        // A duration of 3 seconds and 5 nanoseconds
        Duration duration = Duration.ofSeconds(3, 5);
        Duration oneDay = Duration.between(LocalDate.now().withMonth(1), LocalDate.now());
    }

    static void localDateTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getMonth());
        System.out.println(now.get(ChronoField.MONTH_OF_YEAR));
        // 使用 with 修改已有的 LocalDateTime 对象
        System.out.println(now.withDayOfMonth(1));
        // 使用 plus 加/减已有的 LocalDateTime 对象
        System.out.println(now.plusDays(1));
        // 使用 minus 加/减已有的 LocalDateTime 对象
        System.out.println(now.minusDays(1));
    }

    /**
     * Instant 以时间戳方式计算
     */
    static void instant() {
        Instant instant = Instant.now();
        System.out.println(instant);
    }

    /**
     * 获取两个 Temporal 之间的 Duration
     * 由于 Duration 主要用于以秒和纳秒衡量时间的长短，所以 between 无法应用与 LocalDate 对象作参数
     */
    static void duration() {
        LocalDateTime dateTime1 = LocalDateTime.of(2017, 1, 1, 0, 30, 0);
        LocalDateTime dateTime2 = LocalDateTime.of(2017, 1, 1, 0, 0, 0);
        Duration duration = Duration.between(dateTime1, dateTime2);
        System.out.println(duration);
    }

    /**
     * Period 允许以年、月、日等单位获取多个 Temporal 的时间间隔
     */
    static void period() {
        LocalDate date1 = LocalDate.of(2017, 1, 1);
        LocalDate date2 = LocalDate.of(2017, 1, 2);
        Period period = Period.between(date1, date2);
        System.out.println(period);
    }

    /**
     * 对 Temporal 进行复杂的操作，如将日期调整到下周日，或者是本月的最后一天
     */
    static void temporalAdjuster() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newDateTime = now.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(newDateTime);
    }

    /**
     * 格式化
     */
    static void format() {
        LocalDateTime dateTime = LocalDateTime.now();
        String str = dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(str);
    }

    /**
     * 通过 {区域/城市} 的地区 ID 形式创建 ZoneId，组合 Temporal 实例可以构造 ZonedDateTime 对象
     */
    static void zone() {
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        ZonedDateTime dateTime = LocalDateTime.now().atZone(zoneId);
        System.out.println(dateTime);
    }

    /**
     * 不同的日历
     */
    static void calendar() {
        LocalDateTime dateTime = LocalDateTime.now();
        JapaneseDate date = JapaneseDate.from(dateTime);
        System.out.println(date);
    }

    public static void main(String[] args) {
        zone();
    }

}
