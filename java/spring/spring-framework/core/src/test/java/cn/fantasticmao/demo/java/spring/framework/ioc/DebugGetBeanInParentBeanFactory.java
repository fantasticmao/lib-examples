package cn.fantasticmao.demo.java.spring.framework.ioc;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DebugGetBeanInParentBeanFactory
 *
 * @author fantasticmao
 * @since 2021-01-12
 */
public class DebugGetBeanInParentBeanFactory {

    @Test
    public void debugGetBeanInParentBeanFactory() {
        // debug BeanFactory#getBean(String) 的双亲委派模型
        try (AnnotationConfigApplicationContext parent
                 = new AnnotationConfigApplicationContext(ParentConfiguration.class)) {
            String foo = parent.getBean("foo", String.class);
            Assert.assertEquals("parent", foo);

            try (AnnotationConfigApplicationContext children
                     = new AnnotationConfigApplicationContext(ChildrenConfiguration.class)) {
                children.setParent(parent);

                foo = children.getBean("foo", String.class);
                Assert.assertEquals("parent", foo);
            }
        }
    }

    @Configuration
    static class ParentConfiguration {

        @Bean
        public String foo() {
            return "parent";
        }
    }

    @Configuration
    static class ChildrenConfiguration {

        @Bean
        public String bar() {
            return "children";
        }
    }
}
