package cn.fantasticmao.demo.java.spring.framework.transaction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * TransactionDebugger
 *
 * @author fantasticmao
 * @since 2019-08-15
 */
public class TransactionDebugger {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        UserService userService = applicationContext.getBean(UserService.class);
        User user = new User("Java");
        userService.selfInvocationWillNotLeadToAnActualTransaction(user);
    }
}
