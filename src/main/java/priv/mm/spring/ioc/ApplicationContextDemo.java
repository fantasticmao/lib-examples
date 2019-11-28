package priv.mm.spring.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ApplicationContextDemo
 *
 * @author maodh
 * @since 2018/7/26
 */
public class ApplicationContextDemo {

    @Test
    public void test() {
        // debug IoC 容器的初始化
        String configLocation = ApplicationContextDemo.class.getResource("applicationContext.xml").toString();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);
        // debug IoC 容器的依赖注入
        TestBean project = applicationContext.getBean("user", TestBean.class);
        System.out.println(project);
    }

    public static class TestBean {
        private String name;
        private int age;

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
}