package cn.fantasticmao.demo.java.others.bytebuddy;

import net.bytebuddy.asm.Advice;

/**
 * InterceptionByAdvice
 *
 * @author fantasticmao
 * @since 2020-03-23
 */
public class InterceptionByAdvice {

    @Advice.OnMethodEnter
    public static void onMethodEnter() {
        System.out.println("!!!!!!!!!!!");
    }
}
