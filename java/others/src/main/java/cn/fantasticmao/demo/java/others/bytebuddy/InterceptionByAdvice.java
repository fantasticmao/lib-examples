package cn.fantasticmao.demo.java.others.bytebuddy;

import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * InterceptionByAdvice
 *
 * @author fantasticmao
 * @since 2020-03-23
 */
public class InterceptionByAdvice {
    public static final Logger log = LoggerFactory.getLogger(InterceptionByAdvice.class);

    @Advice.OnMethodEnter
    public static void onMethodEnter() {
        log.info("!!!!!!!!!!!");
    }
}
