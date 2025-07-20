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
 * DateControllerTest
 *
 * @author fantasticmao
 * @since 2022-09-26
 */
public class DateControllerTest extends SpringTest {
    @Resource
    private MockMvc mvc;

    @Test
    public void date_serverError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/date?datetime={datetime}", "hello"))
            .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    public void date_ok() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/date?datetime={datetime}", "2022-09-26 03:00:00"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andDo(result -> {
                MockHttpServletResponse response = result.getResponse();
                String body = response.getContentAsString();
                Assert.assertEquals("to timestamp: 1664132400000", body);
            });
    }
}
