package cn.fantasticmao.demo.java.lang.jsr303;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

/**
 * ValidationTest
 *
 * @author fantasticmao
 * @since 2025-07-18
 */
public class ValidationTest {
    @Test
    public void example() {
        try (ValidatorFactory factory = Validation.byDefaultProvider().configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()) {
            Validator validator = factory.getValidator();

            Param param = new Param();
            param.setCount(-1L);
            param.setAmount(BigDecimal.valueOf(99.99));
            param.setMessage("hello world");
            param.setBirthday(LocalDate.of(2020, 1, 1));

            Set<ConstraintViolation<Param>> result = validator.validate(param);
            Assert.assertEquals(1, result.size());
        }
    }
}
