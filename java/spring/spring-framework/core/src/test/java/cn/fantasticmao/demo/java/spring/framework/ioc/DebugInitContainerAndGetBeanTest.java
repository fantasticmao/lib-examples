package cn.fantasticmao.demo.java.spring.framework.ioc;

import cn.fantasticmao.demo.java.spring.framework.ioc.bean.User;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * DebugInitContainerAndGetBean
 *
 * @author fantasticmao
 * @since 2021-01-12
 */
public class DebugInitContainerAndGetBeanTest {

    @Test
    public void debugInitContainerAndGetBean() {
        // debug IoC 容器的初始化
        try (AnnotationConfigApplicationContext applicationContext
                 = new AnnotationConfigApplicationContext(AppConfiguration.class)) {
            // debug IoC 容器的依赖注入
            User user = applicationContext.getBean("user", User.class);
            System.out.println(user);
        }
    }

    @Configuration
    static class AppConfiguration {

        @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
        public User user() {
            User user = new User();
            user.setName("tom");
            user.setAge(18);
            return user;
        }

        /**
         * 支持 JSR-250 注解的 {@link org.springframework.beans.factory.config.BeanPostProcessor}
         */
        @Bean
        public CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor() {
            return new CommonAnnotationBeanPostProcessor();
        }
    }
}
