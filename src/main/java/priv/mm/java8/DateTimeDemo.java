package priv.mm.java8;

import java.time.*;

/**
 * DateTimeDemo
 *
 * @author maomao
 * @since 2016.11.20
 */
public class DateTimeDemo {
    public static void main(String[] args) {
        System.out.println(Clock.systemUTC().instant());
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());

        final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
        final LocalDateTime to = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);

        final Duration duration = Duration.between(from, to);
        System.out.println("Duration in days: " + duration.toDays());
        System.out.println("Duration in hours: " + duration.toHours());
    }
}
