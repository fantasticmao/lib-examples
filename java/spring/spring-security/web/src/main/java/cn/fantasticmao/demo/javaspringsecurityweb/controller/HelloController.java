package cn.fantasticmao.demo.javaspringsecurityweb.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * HelloController
 *
 * @author maomao
 * @since 2020-06-29
 */
@Controller
public class HelloController {

    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String hello() {
        return "Hello Spring Security!";
    }
}
