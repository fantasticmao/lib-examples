package cn.fantasticmao.demo.java.spring.framework.webmvc.controller;

import cn.fantasticmao.demo.java.spring.framework.webmvc.MvcConfiguration;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ValidationController 校验请求参数
 *
 * @author fantasticmao
 * @see MvcConfiguration#methodValidationPostProcessor()
 * @see <a href="https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-config/validation.html">Validation</a>
 * @see <a href="https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html">Java Bean Validation</a>
 * @since 2020-06-29
 */
@Validated
@RestController
public class ValidationController {

    @GetMapping(value = "/validate1")
    public String foo(@RequestParam @NotBlank String str) {
        return "OK";
    }

    @GetMapping(value = "/validate2")
    public String bar(@RequestParam @Positive Integer num) {
        return "OK";
    }
}

