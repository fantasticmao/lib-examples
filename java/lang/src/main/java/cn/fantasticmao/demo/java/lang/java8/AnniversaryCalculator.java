package cn.fantasticmao.demo.java.lang.java8;

import java.time.LocalDate;

/**
 * AnniversaryCalculator
 *
 * @author maodh
 * @since 11/03/2018
 */
public class AnniversaryCalculator {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2018, 2, 20);
        System.out.println(date.plusDays(100));
        System.out.println(date.plusDays(200));
        System.out.println(date.plusDays(300));
        System.out.println(date.plusDays(400));
        System.out.println(date.plusDays(500));
    }
}