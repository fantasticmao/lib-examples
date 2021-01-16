package cn.fantasticmao.demo.java.spring.framework.ioc;

import cn.fantasticmao.demo.java.spring.framework.ioc.bean.NestedBeanA;
import cn.fantasticmao.demo.java.spring.framework.ioc.bean.NestedBeanB;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DebugCreateNestedBean
 *
 * @author maomao
 * @since 2021-01-15
 */
public class DebugCreateNestedBean {

    @Test(expected = BeanCurrentlyInCreationException.class)
    public void debugCreateNestedBean() throws Throwable {
        try (AnnotationConfigApplicationContext applicationContext
                 = new AnnotationConfigApplicationContext(NestedConfiguration.class)) {
            NestedBeanA a = applicationContext.getBean(NestedBeanA.class);
            Assert.fail();
        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw e; // root exception
        }
    }

    @Configuration
    static class NestedConfiguration {

        @Bean
        public NestedBeanA a(@Autowired NestedBeanB b) {
            return new NestedBeanA(b);
        }

        @Bean
        public NestedBeanB b(@Autowired NestedBeanA a) {
            return new NestedBeanB(a);
        }
    }
}
