package cn.fantasticmao.demo.java.apache.digester;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * EmployeeParserTest
 *
 * @author fantasticmao
 * @since 2020-05-14
 */
public class EmployeeParserTest {

    @Test
    public void parse() throws IOException, SAXException {
        final Employee employee = EmployeeParser.parse();
        Assert.assertNotNull(employee);
        Assert.assertEquals("Brian", employee.getFirstName());
        Assert.assertEquals("May", employee.getLastName());
        Assert.assertNotNull(employee.getOffice());
        Assert.assertEquals("Wellington Street", employee.getOffice().getAddress().getStreetName());
        Assert.assertEquals("10", employee.getOffice().getAddress().getStreetNumber());
    }
}