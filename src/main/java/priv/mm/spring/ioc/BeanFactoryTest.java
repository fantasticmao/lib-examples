package priv.mm.spring.ioc;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * BeanFactoryTest
 *
 * @author maodh
 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory#doGetBean(String, Class, Object[], boolean)
 * @since 2018/10/31
 */
public class BeanFactoryTest {

    /**
     * 测试 <code>BeanFactory#getBean(String)</code> 的双亲委派模型
     *
     * @see org.springframework.beans.factory.BeanFactory#getBean(String)
     */
    public static void testGetBean() {
        DefaultListableBeanFactory rootBeanFactory = new DefaultListableBeanFactory();
        rootBeanFactory.setSerializationId("Root BeanFactory");

        XmlBeanDefinitionReader rootBeanDefinitionReader = new XmlBeanDefinitionReader(rootBeanFactory);
        rootBeanDefinitionReader.loadBeanDefinitions("classpath:applicationContext.xml");

        String project1 = rootBeanFactory.getBean("project", String.class);
        System.out.println(project1);

        DefaultListableBeanFactory subBeanFactory = new DefaultListableBeanFactory(rootBeanFactory);
        subBeanFactory.setSerializationId("Sub BeanFactory");

        XmlBeanDefinitionReader subBeanDefinitionReader = new XmlBeanDefinitionReader(subBeanFactory);
        subBeanDefinitionReader.loadBeanDefinitions("classpath:applicationSubContext.xml");

        String project2 = subBeanFactory.getBean("project", String.class);
        System.out.println(project2);
    }

    public static void main(String[] args) {
        testGetBean();
    }
}
