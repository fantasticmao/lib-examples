package cn.fantasticmao.demo.java.lang.jsr303;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.executable.ExecutableValidator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ValidationTest
 *
 * @author fantasticmao
 * @since 2025-07-18
 */
public class ValidationTest {

    @Test
    public void validate() {
        ValidatorFactory factory = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory();
        Validator validator = factory.getValidator();

        Param param = new Param();
        param.setCount(-1L);
        param.setAmount(BigDecimal.valueOf(99.99));
        param.setMessage("hello world");
        param.setBirthday(LocalDate.of(2020, 1, 1));

        Set<ConstraintViolation<Param>> result = validator.validate(param);
        Assert.assertEquals(2, result.size());

        Set<String> errMsgSet = result.stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.toSet());
        Assert.assertTrue(errMsgSet.contains("must be greater than 0"));
        Assert.assertTrue(errMsgSet.contains("must be upper case only"));

        factory.close();
    }

    @Test
    public void validateParameters() throws NoSuchMethodException {
        ValidatorFactory factory = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory();
        ExecutableValidator execVal = factory.getValidator().forExecutables();

        Runner target = new Runner();
        Method method = Runner.class.getMethod("run", Long.class, String.class);

        Set<ConstraintViolation<Runner>> result = execVal.validateParameters(target,
            method, new Object[]{-1L, "hello world"});
        Assert.assertEquals(2, result.size());

        Set<String> errMsgSet = result.stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.toSet());
        Assert.assertTrue(errMsgSet.contains("must be greater than 0"));
        Assert.assertTrue(errMsgSet.contains("must be upper case only"));

        factory.close();
    }
}
