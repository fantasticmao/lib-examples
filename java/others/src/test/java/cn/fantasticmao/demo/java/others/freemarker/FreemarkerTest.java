package cn.fantasticmao.demo.java.others.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * FreemarkerTest
 *
 * @author fantasticmao
 * @since 2021-12-21
 */
public class FreemarkerTest {

    @Test
    public void test() throws IOException, TemplateException {
        // Create a configuration instance
        // https://freemarker.apache.org/docs/pgui_quickstart_createconfiguration.html
        Configuration config = new FreemarkerConfig().getConfiguration();

        // Create a data-model
        // https://freemarker.apache.org/docs/pgui_quickstart_createdatamodel.html
        Map<String, Object> root = new HashMap<>();
        root.put("user", "Big Joe");
        Product latest = new Product();
        latest.setUrl("products/greenmouse.html");
        latest.setName("green mouse");
        root.put("latestProduct", latest);

        // Get the template
        // https://freemarker.apache.org/docs/pgui_quickstart_gettemplate.html
        Template template = config.getTemplate("freemarker.ftlh");

        // Merging the template with the data-model
        // https://freemarker.apache.org/docs/pgui_quickstart_merge.html
        Writer writer = new OutputStreamWriter(System.out);
        template.process(root, writer);
    }
}
