package cn.fantasticmao.demo.java.lang.jsr269;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * SoonDeprecated
 *
 * @author fantasticmao
 * @since 2022-12-19
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface SoonDeprecated {

    String version();

}
