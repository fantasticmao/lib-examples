package cn.fantasticmao.demo.java.spring.framework.webmvc.controller;

import cn.fantasticmao.demo.java.spring.framework.webmvc.MvcConfiguration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * ValidationController 校验请求参数
 *
 * @author fantasticmao
 * @see MvcConfiguration#methodValidationPostProcessor()
 * @see <a href="https://docs.spring.io/spring/docs/5.2.6.RELEASE/spring-framework-reference/web.html#mvc-config-validation">Validation</a>
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

