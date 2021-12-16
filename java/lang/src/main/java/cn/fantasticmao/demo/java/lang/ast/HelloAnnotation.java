package cn.fantasticmao.demo.java.lang.ast;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HelloAnnotation
 *
 * @author fantasticmao
 * @since 2020-01-09
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface HelloAnnotation {

    String username();
}
