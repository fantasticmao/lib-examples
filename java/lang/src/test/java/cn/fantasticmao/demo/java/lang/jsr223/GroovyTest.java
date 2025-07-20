package cn.fantasticmao.demo.java.lang.jsr223;

import org.junit.Assert;
import org.junit.Test;

import javax.script.ScriptException;

/**
 * GroovyTest
 *
 * @author fantasticmao
 * @since 2022-11-01
 */
public class GroovyTest {

    @Test
    public void sum() throws ScriptException {
        Integer sum = new GroovyCommand().sum(1, 10);
        Assert.assertEquals(Integer.valueOf(55), sum);
    }

    @Test
    public void hello() throws ScriptException {
        String result = new GroovyCommand().upperCasePrint("hello", "world");
        Assert.assertEquals("HELLO WORLD", result);
    }
}
