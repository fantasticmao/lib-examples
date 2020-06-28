package cn.fantasticmao.demo.java.spring.framework.webmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * HelloController
 *
 * @author maomao
 * @since 2020-06-28
 */
@Controller
public class HelloController {

    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String hello() {
        return "Hello World!";
    }

    @GetMapping(value = "/say/{msg}", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String say(@PathVariable String msg) {
        return msg;
    }

}
