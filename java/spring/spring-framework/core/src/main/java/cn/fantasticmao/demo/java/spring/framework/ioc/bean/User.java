package cn.fantasticmao.demo.java.spring.framework.ioc.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;

/**
 * User
 *
 * <p>Spring 在 Bean 生命周期中提供了三类回调方法，分别是基于 {@link PostConstruct} 和 {@link PreDestroy} JSR-205 注解、
 * 基于 {@link InitializingBean} 和 {@link DisposableBean} Spring 内置接口、基于 {@link Bean#initMethod()} 和
 * {@link Bean#destroyMethod()} 配置 Bean 时指定方法的。这三类方法的调用优先级为：基于 JSR-205 注解 -> 基于 Spring 内置接口 -> 配置 Bean 时指定方法。
 *
 * <p>注意，Spring 不会完整管理 prototype Bean 的生命周期，prototype Bean 相关的销毁方法需要开发者自己调用。具体请见
 * <a href="https://docs.spring.io/spring/docs/5.2.7.RELEASE/spring-framework-reference/core.html#beans-factory-scopes-prototype">官方文档</a>。
 *
 * @author fantasticmao
 * @see <a href="https://docs.spring.io/spring/docs/5.2.7.RELEASE/spring-framework-reference/core.html#beans-factory-lifecycle">Spring Bean 生命周期中的一些回调方法</a>
 * @since 2020-06-28
 */
public class User implements InitializingBean, DisposableBean {
    private String name;
    private int age;

    /**
     * JSR-250 注解 @PostConstruct
     */
    @PostConstruct
    public void postConstruct() {
        System.out.printf("%s postConstruct%n", this.getClass().getName());
    }

    /**
     * JSR-250 注解 @PreDestroy
     */
    @PreDestroy
    public void preDestroy() {
        System.out.printf("%s preDestroy%n", this.getClass().getName());
    }

    /**
     * InitializingBean
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.printf("%s InitializingBean afterPropertiesSet%n", this.getClass().getName());
    }

    /**
     * DisposableBean
     */
    @Override
    public void destroy() throws Exception {
        System.out.printf("%s DisposableBean destroy%n", this.getClass().getName());
    }

    /**
     * {@link Bean#initMethod()}
     */
    public void initMethod() {
        System.out.printf("%s init method%n", this.getClass().getName());
    }

    /**
     * {@link Bean#destroyMethod()}
     */
    public void destroyMethod() {
        System.out.printf("%s destroy method%n", this.getClass().getName());
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }

    // getter and setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
