package cn.fantasticmao.demo.java.spring.framework.webmvc;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * SpringTest
 *
 * @author fantasticmao
 * @since 2022-09-26
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {MvcConfiguration.class, MvcTestConfiguration.class})
public class SpringTest {
}
