package cn.fantasticmao.demo.java.spring.framework.webmvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * MvcConfiguration
 *
 * @author fantasticmao
 * @since 2020-06-29
 */
@EnableWebMvc
@ComponentScan
@Configuration
public class MvcConfiguration implements WebApplicationInitializer, WebMvcConfigurer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(MvcConfiguration.class);

        DispatcherServlet servlet = new DispatcherServlet(applicationContext);
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }

    /**
     * 处理静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/public/");
    }

    /**
     * 处理跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // test curl command:
        // curl -v --request GET \
        //  --url http://localhost:8080/hello \
        //  --header 'origin: https://fantasticmao.cn'
        registry.addMapping("/hello")
            .allowedOrigins("https://fantasticmao.cn");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index.html");
    }

    /**
     * 支持在 Controller 方法中使用 Bean Validation
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
