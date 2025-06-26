package cn.fantasticmao.demo.java.lang.java10;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * VarTest
 *
 * @author fantasticmao
 * @since 2025-06-26
 */
public class VarTest {

    @Test
    public void var() {
        var multiValueMap = Map.of(
            "arabic", List.of("1", "2", "3"),
            "english", List.of("one", "two", "three"),
            "chinese", List.of("一", "二", "三")
        );
        Assert.assertEquals(3, multiValueMap.size());
    }
}
