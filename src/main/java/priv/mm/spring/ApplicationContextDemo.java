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
        // debug IoC 容器的初始化
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        // debug IoC 容器的依赖注入
        TestBean project = applicationContext.getBean("user", TestBean.class);
        System.out.println(project);
    }

    public static class TestBean{
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