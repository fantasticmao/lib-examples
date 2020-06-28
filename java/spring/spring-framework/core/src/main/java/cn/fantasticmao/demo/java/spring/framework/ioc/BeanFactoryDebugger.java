package cn.fantasticmao.demo.java.spring.framework.ioc;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * BeanFactoryDebugger
 *
 * @author maodh
 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory#doGetBean(String, Class, Object[], boolean)
 * @since 2018/10/31
 */
public class BeanFactoryDebugger {

    /**
     * 测试 <code>BeanFactory#getBean(String)</code> 的双亲委派模型
     *
     * @see org.springframework.beans.factory.BeanFactory#getBean(String)
     */
    public static void main(String[] args) {
        DefaultListableBeanFactory rootBeanFactory = new DefaultListableBeanFactory();
        rootBeanFactory.setSerializationId("Root BeanFactory");

        XmlBeanDefinitionReader rootBeanDefinitionReader = new XmlBeanDefinitionReader(rootBeanFactory);
        rootBeanDefinitionReader.loadBeanDefinitions(BeanFactoryDebugger.class.getResource("/applicationContext.xml").toString());

        String project1 = rootBeanFactory.getBean("project", String.class);
        System.out.println("Root BeanFactory: " + project1);

        DefaultListableBeanFactory subBeanFactory = new DefaultListableBeanFactory(rootBeanFactory);
        subBeanFactory.setSerializationId("Sub BeanFactory");

        XmlBeanDefinitionReader subBeanDefinitionReader = new XmlBeanDefinitionReader(subBeanFactory);
        subBeanDefinitionReader.loadBeanDefinitions(BeanFactoryDebugger.class.getResource("/applicationSubContext.xml").toString());

        String project2 = subBeanFactory.getBean("project", String.class);
        System.out.println("Sub BeanFactory: " + project2);
    }

}
