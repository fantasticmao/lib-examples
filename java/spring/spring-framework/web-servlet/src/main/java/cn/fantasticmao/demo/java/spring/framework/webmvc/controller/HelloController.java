package cn.fantasticmao.demo.java.spring.framework.webmvc.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author fantasticmao
 * @since 2020-06-28
 */
@RestController
public class HelloController {

    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String hello() {
        return "Hello Spring Web MVC!";
    }

    @GetMapping(value = "/say/{msg}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String say(@PathVariable String msg) {
        return "{\"msg\":\"" + msg + "\"}";
    }

}
