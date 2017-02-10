package priv.mm.java8;

import junit.framework.Assert;

import java.time.*;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

/**
 * DateTimeDemo
 *
 * @author maomao
 * @since 2016.11.20
 */
public class DateTimeDemo {
    public static void main(String[] args) {
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


        LocalDateTime foo = timePoint.with(lastDayOfMonth());
        LocalDateTime bar = timePoint.with(previousOrSame(DayOfWeek.WEDNESDAY));
        // Using value classes as adjusters
        timePoint.with(LocalTime.now());


        LocalTime truncatedTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);


        // You can specify the zone id when creating a zoned date time
        ZoneId id = ZoneId.of("Europe/Paris");
        ZonedDateTime zoned = ZonedDateTime.of(LocalDateTime.now(), id);
        Assert.assertEquals(id, ZoneId.from(zoned));


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
        Assert.assertEquals(1, period.get(ChronoUnit.DAYS));


        // A duration of 3 seconds and 5 nanoseconds
        Duration duration = Duration.ofSeconds(3, 5);
        Duration oneDay = Duration.between(LocalDate.now().withMonth(1), LocalDate.now());
    }

}
