package cn.fantasticmao.demo.java.spring.framework.ioc;

import cn.fantasticmao.demo.java.spring.framework.ioc.bean.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ApplicationContextDebugger
 *
 * @author maodh
 * @since 2018/7/26
 */
public class ApplicationContextDebugger {

    public static void main(String[] args) {
        // debug IoC 容器的初始化
        final String configLocation = ApplicationContextDebugger.class.getResource("/applicationContext.xml").toString();
        try (ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);) {
            // debug IoC 容器的依赖注入
            User user = applicationContext.getBean("user", User.class);
            System.out.println(user);
        }
    }
}