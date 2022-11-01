package cn.fantasticmao.demo.java.others.groovy;

import org.junit.Assert;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * GroovyTest
 *
 * @author fantasticmao
 * @since 2022-11-01
 */
public class GroovyTest {
    private final ScriptEngineManager factory = new ScriptEngineManager();
    private final ScriptEngine engine = factory.getEngineByName("groovy");

    @Test
    public void sum() throws ScriptException {
        Integer sum = (Integer) engine.eval("(1..10).sum()");
        Assert.assertEquals(Integer.valueOf(55), sum);
    }

    @Test
    public void hello() throws ScriptException {
        engine.put("first", "HELLO");
        engine.put("second", "world");
        String result = (String) engine.eval("first.toLowerCase() + ' ' + second.toUpperCase()");
        Assert.assertEquals("hello WORLD", result);
    }
}
