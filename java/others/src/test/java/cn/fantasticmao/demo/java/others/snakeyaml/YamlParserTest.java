package cn.fantasticmao.demo.java.others.snakeyaml;

import cn.fantasticmao.demo.java.others.Employee;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * YamlParserTest
 *
 * @author fantasticmao
 * @since 2022-03-17
 */
public class YamlParserTest {

    @Test
    public void parse() throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("employee.yml")) {
            final Employee employee = new YamlParser().parse(input);
            Assert.assertNotNull(employee);
            Assert.assertEquals("Brian", employee.getFirstName());
            Assert.assertEquals("May", employee.getLastName());
            Assert.assertNotNull(employee.getOffice());
            Assert.assertEquals("Wellington Street", employee.getOffice().getAddress().getStreetName());
            Assert.assertEquals("10", employee.getOffice().getAddress().getStreetNumber());
        }
    }
}
