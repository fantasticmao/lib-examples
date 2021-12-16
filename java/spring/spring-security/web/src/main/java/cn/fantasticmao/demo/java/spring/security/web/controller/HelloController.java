package cn.fantasticmao.demo.java.spring.security.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * HelloController
 *
 * @author fantasticmao
 * @since 2020-06-29
 */
@Controller
public class HelloController {

    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String hello(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("username", "Tom");
        return "Hello Spring Security!";
    }
}
