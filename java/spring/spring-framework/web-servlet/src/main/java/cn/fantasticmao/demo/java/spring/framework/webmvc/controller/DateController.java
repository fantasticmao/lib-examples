package cn.fantasticmao.demo.java.spring.framework.webmvc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * DateController
 *
 * @author maomao
 * @see GeneralControllerAdvice#initBinder(WebDataBinder)
 * @since 2020-06-29
 */
@Controller
@ResponseBody
public class DateController {

    @GetMapping(value = "/date", produces = MediaType.TEXT_PLAIN_VALUE)
    public String date(@RequestParam Date datetime) {
        return "to timestamp: " + datetime.getTime();
    }
}
