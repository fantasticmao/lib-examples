package cn.fantasticmao.demo.java.spring.framework.tx;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * SpringTransactionDemo
 *
 * @author maomao
 * @since 2019-08-15
 */
public class SpringTransactionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        UserService userService = applicationContext.getBean(UserService.class);
        User user = new User("Tom");
        userService.selfInvocationWillNotLeadToAnActualTransaction(user);
    }
}
