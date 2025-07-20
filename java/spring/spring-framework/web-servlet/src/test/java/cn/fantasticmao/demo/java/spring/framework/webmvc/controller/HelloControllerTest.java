package cn.fantasticmao.demo.java.spring.framework.webmvc.controller;

import cn.fantasticmao.demo.java.spring.framework.webmvc.SpringTest;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * HelloControllerTest
 *
 * @author fantasticmao
 * @since 2022-09-26
 */
public class HelloControllerTest extends SpringTest {
    @Resource
    private MockMvc mvc;

    @Test
    public void hello_ok() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andDo(result -> {
                MockHttpServletResponse response = result.getResponse();
                String body = response.getContentAsString();
                Assert.assertEquals("Hello Spring Web MVC!", body);
            });
    }

    @Test
    public void say_ok() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/say/{msg}", "hello"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andDo(result -> {
                MockHttpServletResponse response = result.getResponse();
                String body = response.getContentAsString();
                Assert.assertEquals("{\"msg\":\"hello\"}", body);
            });
    }
}
