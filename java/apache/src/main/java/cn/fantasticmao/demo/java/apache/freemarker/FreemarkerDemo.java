package cn.fantasticmao.demo.java.apache.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * FreemarkerDemo
 *
 * @author maomao
 * @since 2019-11-27
 */
public class FreemarkerDemo {

    public static void main(String[] args) throws Exception {
        FreemarkerDemo demo = new FreemarkerDemo();

        // Create and adjust the configuration singleton
        Configuration configuration = demo.getConfiguration();

        // Create a data-model
        Map<String, Object> data = demo.getData("Big Joe");

        // Get the template (uses cache internally)
        Template template = configuration.getTemplate("test.ftlh");

        // Merge data-model with template
        Writer out = new OutputStreamWriter(System.out);
        template.process(data, out);
    }

    public Configuration getConfiguration() throws IOException, URISyntaxException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        URI templateUri = FreemarkerDemo.class.getResource(".").toURI();
        cfg.setDirectoryForTemplateLoading(new File(templateUri));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        return cfg;
    }

    private Map<String, Object> getData(String user) {
        Map<String, Object> root = new HashMap<>();
        root.put("user", user);
        Product latest = new Product();
        latest.setUrl("products/greenmouse.html");
        latest.setName("green mouse");
        root.put("latestProduct", latest);
        return root;
    }

    public static class Product {
        private String url;
        private String name;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
