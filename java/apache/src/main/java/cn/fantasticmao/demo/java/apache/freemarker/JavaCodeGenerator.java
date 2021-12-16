package cn.fantasticmao.demo.java.apache.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * JavaCodeGenerator
 *
 * @author fantasticmao
 * @since 2019-11-28
 */
public class JavaCodeGenerator {

    public static void main(String[] args) throws Exception {
        final String packagePath = "test";
        final String className = "User";
        final String fieldName = "name";
        final String fieldNameCamelCase = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        FreemarkerDemo demo = new FreemarkerDemo();
        Configuration configuration = demo.getConfiguration();

        Map<String, String> data = new HashMap<>();
        data.put("packagePath", packagePath);
        data.put("className", className);
        data.put("fieldName", fieldName);
        data.put("fieldNameCamelCase", fieldNameCamelCase);

        Template template = configuration.getTemplate("User.java");

        Writer out = new OutputStreamWriter(System.out);
        template.process(data, out);
    }
}
