package cn.fantasticmao.demo.java.spring.framework.webmvc.controller;

import cn.fantasticmao.demo.java.spring.framework.webmvc.SpringTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

/**
 * ValidationControllerTest
 *
 * @author fantasticmao
 * @since 2022-09-26
 */
public class ValidationControllerTest extends SpringTest {
    @Resource
    private MockMvc mvc;

    @Test
    public void foo_serverError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/validate1"))
            .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    public void foo_ok() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/validate1?str={str}", "hello"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andDo(result -> {
                MockHttpServletResponse response = result.getResponse();
                String body = response.getContentAsString();
                Assert.assertEquals("OK", body);
            });
    }

    @Test
    public void bar_clientError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/validate2/num={num}", "-1"))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void bar_ok() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/validate2?num={num}", "6"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andDo(result -> {
                MockHttpServletResponse response = result.getResponse();
                String body = response.getContentAsString();
                Assert.assertEquals("OK", body);
            });
    }
}