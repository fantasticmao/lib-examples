package cn.fantasticmao.demo.java.spring.framework.ioc.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * DynamicBeanDefinitionPostProcessor
 *
 * @author fantasticmao
 * @since 2025-08-05
 */
public class DynamicBeanDefinitionPostProcessor implements BeanDefinitionRegistryPostProcessor {
    public static final String BEAN_NAME_1 = "dynamicBeanDefinition_1";
    public static final String BEAN_NAME_2 = "dynamicBeanDefinition_2";

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(ArrayList.class);
        beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        registry.registerBeanDefinition(BEAN_NAME_1, beanDefinition);

        beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(HashMap.class);
        beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        registry.registerBeanDefinition(BEAN_NAME_2, beanDefinition);
    }
}
