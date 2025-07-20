package cn.fantasticmao.demo.java.lang.jsr223;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * GroovyCommand
 *
 * @author fantasticmao
 * @since 2025-07-18
 */
public class GroovyCommand {
    private final ScriptEngineManager factory = new ScriptEngineManager();
    private final ScriptEngine engine = factory.getEngineByName("groovy");

    public Integer sum(Integer start, Integer end) throws ScriptException {
        String script = String.format("(%d..%d).sum()", start, end);
        return (Integer) engine.eval(script);
    }

    public String upperCasePrint(String str1, String str2) throws ScriptException {
        engine.put("str1", str1);
        engine.put("str2", str2);
        return (String) engine.eval("str1.toUpperCase() + ' ' + str2.toUpperCase()");
    }
}
