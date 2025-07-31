package cn.fantasticmao.demo.java.lang.jsr303;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * UppercaseOnly
 *
 * @author fantasticmao
 * @since 2025-07-30
 */
@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UppercaseOnly {
    String message() default "must be upper case only";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
