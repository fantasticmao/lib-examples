package cn.fantasticmao.demo.java.spring.framework.webmvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * MvcTestConfiguration
 *
 * @author fantasticmao
 * @since 2022-09-26
 */
@Configuration
public class MvcTestConfiguration {

    @Bean
    public MockMvc mockMvc(WebApplicationContext context) {
        return MockMvcBuilders.webAppContextSetup(context).build();
    }
}
