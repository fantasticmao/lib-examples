package cn.fantasticmao.demo.java.lang.asm.bytebuddy;

import net.bytebuddy.asm.Advice;

/**
 * InterceptionByAdvice
 *
 * @author maomao
 * @since 2020-03-23
 */
public class InterceptionByAdvice {

    @Advice.OnMethodEnter
    public static void onMethodEnter() {
        System.out.println("!!!!!!!!!!!");
    }
}
