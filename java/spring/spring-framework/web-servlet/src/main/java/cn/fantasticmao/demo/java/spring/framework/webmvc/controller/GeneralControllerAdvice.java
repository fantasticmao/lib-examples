package cn.fantasticmao.demo.java.spring.framework.webmvc.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * GeneralControllerAdvice
 *
 * @author fantasticmao
 * @see <a href="https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-advice.html">Controller Advice</a>
 * @since 2020-06-29
 */
@ControllerAdvice
public class GeneralControllerAdvice {

    /**
     * DataBinder
     *
     * @see <a href="https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-initbinder.html">InitBinder</a>
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor customDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    /**
     * Exceptions
     *
     * @see <a href="https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-exceptionhandler.html">Exceptions</a>
     */
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<String> exceptionHandler(Exception e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
