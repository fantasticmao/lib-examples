/**
 * Spring IoC
 *
 * <p>{@link org.springframework.beans.factory.config.BeanPostProcessor} 支持创建 Bean 实例之前和之后的钩子方法。具体使用案例
 * 例如 {@code EnvironmentAware}、{@code ResourceLoaderAware}、{@code ApplicationContextAware} 接口，它们是通过
 * {@link org.springframework.context.support.ApplicationContextAwareProcessor} 来实现的。
 * 再如 {@link javax.annotation.Resource}、{@link javax.annotation.PostConstruct}、{@link javax.annotation.PreDestroy}
 * 注解，它们是通过 {@link org.springframework.context.annotation.CommonAnnotationBeanPostProcessor} 来实现的。
 *
 * <p>{@link org.springframework.beans.factory.config.BeanFactoryPostProcessor}
 *
 * <p>{@link org.springframework.beans.factory.FactoryBean#getObject()} 支持定制创建 Bean 实例。
 *
 * @author maomao
 * @since 2020-06-30
 */
package cn.fantasticmao.demo.java.spring.framework.ioc;