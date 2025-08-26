package cn.fantasticmao.demo.java.spring.framework.webmvc.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    @GetMapping(value = "/date", produces = MediaType.TEXT_PLAIN_VALUE)
    public String date(@RequestParam Date datetime) {
        return "to timestamp: " + datetime.getTime();
    }

    @GetMapping(value = "/logs")
    public SseEmitter logs() {
        SseEmitter emitter = new SseEmitter();
        Thread.startVirtualThread(() -> {
            for (int i = 0; i < 10; i++) {
                SseEmitter.SseEventBuilder builder = SseEmitter.event()
                    .id(String.valueOf(i))
                    .data("Timestamp: " + System.currentTimeMillis());

                try {
                    emitter.send(builder);
                    TimeUnit.SECONDS.sleep(1);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return emitter;
    }
}
