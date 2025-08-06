package cn.fantasticmao.demo.java.spring.framework.ioc;

import cn.fantasticmao.demo.java.spring.framework.ioc.bean.DynamicBeanDefinitionPostProcessor;
import cn.fantasticmao.demo.java.spring.framework.ioc.bean.DynamicBeanPostProcessor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * DynamicBeanTest
 *
 * @author fantasticmao
 * @since 2025-08-05
 */
public class DynamicBeanTest {
    @Test
    public void testDynamicBeanInject() {
        try (AnnotationConfigApplicationContext applicationContext
                 = new AnnotationConfigApplicationContext(AppConfiguration.class)) {
            String dynamicBean = applicationContext.getBean(DynamicBeanPostProcessor.BEAN_NAME_1, String.class);
            Assert.assertEquals(dynamicBean, "hello dynamic bean");
            dynamicBean = applicationContext.getBean(DynamicBeanPostProcessor.BEAN_NAME_2, String.class);
            Assert.assertEquals(dynamicBean, "hello dynamic bean again");

            Object dynamicBeanDefinition = applicationContext.getBean(DynamicBeanDefinitionPostProcessor.BEAN_NAME_1);
            Assert.assertTrue(List.class.isAssignableFrom(dynamicBeanDefinition.getClass()));
            dynamicBeanDefinition = applicationContext.getBean(DynamicBeanDefinitionPostProcessor.BEAN_NAME_2);
            Assert.assertTrue(Map.class.isAssignableFrom(dynamicBeanDefinition.getClass()));
        }
    }

    @Configuration
    static class AppConfiguration {

        @Bean
        public static DynamicBeanPostProcessor dynamicBeanPostProcessor() {
            return new DynamicBeanPostProcessor();
        }

        @Bean
        public static DynamicBeanDefinitionPostProcessor dynamicBeanDefinitionPostProcessor() {
            return new DynamicBeanDefinitionPostProcessor();
        }
    }
}
