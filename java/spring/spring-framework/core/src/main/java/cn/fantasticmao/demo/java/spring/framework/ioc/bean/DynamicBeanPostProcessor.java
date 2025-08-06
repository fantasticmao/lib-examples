package cn.fantasticmao.demo.java.spring.framework.ioc.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * DynamicBeanPostProcessor
 *
 * @author fantasticmao
 * @since 2025-08-05
 */
public class DynamicBeanPostProcessor implements BeanFactoryPostProcessor {
    public static final String BEAN_NAME_1 = "dynamicBean_1";
    public static final String BEAN_NAME_2 = "dynamicBean_2";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerSingleton(BEAN_NAME_1, "hello dynamic bean");
        beanFactory.registerSingleton(BEAN_NAME_2, "hello dynamic bean again");
    }
}
