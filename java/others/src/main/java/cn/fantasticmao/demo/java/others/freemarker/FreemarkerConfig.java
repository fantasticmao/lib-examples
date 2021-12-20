package cn.fantasticmao.demo.java.others.freemarker;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.net.URL;
import java.util.Objects;

/**
 * FreemarkerConfig
 *
 * @author fantasticmao
 * @see <a href="https://freemarker.apache.org/docs/pgui_quickstart.html">Getting Started</a>
 * @since 2019-11-27
 */
public class FreemarkerConfig {

    public Configuration getConfiguration() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        URL templateUrl = this.getClass().getResource("/");
        Objects.requireNonNull(templateUrl);
        cfg.setTemplateLoader(new ClassTemplateLoader(this.getClass(), "/"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        return cfg;
    }

}
