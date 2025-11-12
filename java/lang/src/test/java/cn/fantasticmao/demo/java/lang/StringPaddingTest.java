package cn.fantasticmao.demo.java.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * StringPaddingTest
 *
 * @author fantasticmao
 * @since 2020-05-14
 */
@Slf4j
public class StringPaddingTest {

    @Test
    public void stringPadding() {
        List<String> stringList = Arrays.asList("1", "22", "333", "4444");
        for (String str : stringList) {
            log.info("#{}#", String.format("%-20s", str));
        }
        for (String str : stringList) {
            log.info("#{}#", String.format("%20s", str));
        }
    }

    @Test
    public void numberPadding() {
        List<Integer> integerList = Arrays.asList(1, 22, 333, 4444);
        for (Integer integer : integerList) {
            log.info("#{}#", String.format("%-6d", integer));
        }
        for (Integer integer : integerList) {
            log.info("#{}#", String.format("%6d", integer));
        }
    }

}
