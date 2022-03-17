package cn.fantasticmao.demo.java.others.digester;

import cn.fantasticmao.demo.java.others.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

/**
 * DigesterParserTest
 *
 * @author fantasticmao
 * @since 2021-12-19
 */
public class DigesterParserTest {

    @Test
    public void parse() throws IOException, SAXException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream input = classLoader.getResourceAsStream("employee.xml")) {
            final Employee employee = new DigesterParser().parse(input);
            Assert.assertNotNull(employee);
            Assert.assertEquals("Brian", employee.getFirstName());
            Assert.assertEquals("May", employee.getLastName());
            Assert.assertNotNull(employee.getOffice());
            Assert.assertEquals("Wellington Street", employee.getOffice().getAddress().getStreetName());
            Assert.assertEquals("10", employee.getOffice().getAddress().getStreetNumber());
        }
    }
}