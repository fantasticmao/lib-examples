package cn.fantasticmao.demo.java.lang.java14;

import org.junit.Test;

import java.util.Random;

/**
 * SwitchTest
 *
 * @author fantasticmao
 * @since 2025-06-26
 */
public class SwitchTest {

    @Test
    public void switchEnhance() {
        int random = new Random().nextInt(10);
        String award = switch (random) {
            case 6 -> "一等奖";
            case 2, 7 -> "二等奖";
            case 1, 3, 5 -> "三等奖";
            default -> "阳光普照";
        };
        System.out.println("random: " + random + ", award: " + award);
    }

}
