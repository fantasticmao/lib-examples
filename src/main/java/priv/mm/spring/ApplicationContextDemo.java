package priv.mm.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ApplicationContextDemo
 *
 * @author maodh
 * @since 2018/7/26
 */
public class ApplicationContextDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        String project = applicationContext.getBean("project", String.class);
        System.out.println(project);
    }
}
