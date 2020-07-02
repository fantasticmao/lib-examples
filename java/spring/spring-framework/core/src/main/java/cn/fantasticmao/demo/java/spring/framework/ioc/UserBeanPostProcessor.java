package cn.fantasticmao.demo.java.spring.framework.ioc;

import cn.fantasticmao.demo.java.spring.framework.ioc.bean.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.Nullable;

/**
 * UserBeanPostProcessor
 *
 * @author maomao
 * @since 2020-07-01
 */
public class UserBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(@Nullable Object bean, String beanName) throws BeansException {
        if (bean instanceof User) {
            System.out.printf("%s UserBeanPostProcessor beforeInitialization%n", bean.getClass().getName());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(@Nullable Object bean, String beanName) throws BeansException {
        if (bean instanceof User) {
            System.out.printf("%s UserBeanPostProcessor afterInitialization%n", bean.getClass().getName());
        }
        return bean;
    }
}
