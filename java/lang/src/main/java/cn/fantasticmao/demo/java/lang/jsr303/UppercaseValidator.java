package cn.fantasticmao.demo.java.lang.jsr303;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * UppercaseValidator
 *
 * @author fantasticmao
 * @since 2025-07-30
 */
public class UppercaseValidator implements ConstraintValidator<UppercaseOnly, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.matches("[A-Z]+");
    }
}
