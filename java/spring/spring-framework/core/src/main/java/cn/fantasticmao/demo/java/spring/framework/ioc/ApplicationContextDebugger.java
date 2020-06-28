package cn.fantasticmao.demo.java.spring.framework.ioc;

import org.springframework.context.ApplicationContext;
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
        String configLocation = ApplicationContextDebugger.class.getResource("/applicationContext.xml").toString();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);
        // debug IoC 容器的依赖注入
        UserBean user = applicationContext.getBean("user", UserBean.class);
        System.out.println(user);
    }
}