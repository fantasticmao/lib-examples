package cn.fantasticmao.demo.java.lang.java12;

import org.junit.Assert;
import org.junit.Test;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * NumberFormatTest
 *
 * @author fantasticmao
 * @since 2022-12-23
 */
public class NumberFormatTest {

    @Test
    public void format() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        String text = numberFormat.format(1_000_000);
        Assert.assertEquals("1,000,000", text);

        numberFormat = NumberFormat.getCompactNumberInstance(Locale.ENGLISH, NumberFormat.Style.SHORT);
        text = numberFormat.format(1_000);
        Assert.assertEquals("1K", text);

        numberFormat = NumberFormat.getCompactNumberInstance(Locale.ENGLISH, NumberFormat.Style.LONG);
        text = numberFormat.format(1_000);
        Assert.assertEquals("1 thousand", text);

        numberFormat = NumberFormat.getCompactNumberInstance(Locale.CHINESE, NumberFormat.Style.SHORT);
        text = numberFormat.format(1_000_000);
        Assert.assertEquals("100万", text);
    }
}
