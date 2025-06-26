package cn.fantasticmao.demo.java.lang.java11;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * FilesTest
 *
 * @author fantasticmao
 * @since 2022/3/1
 */
public class FilesTest {

    @Test
    public void readString() throws IOException {
        URL url = getClass().getResource("/io/test.txt");
        Assert.assertNotNull(url);

        Path path = Paths.get(url.getPath());
        String str = Files.readString(path);
        Assert.assertNotNull(str);

        System.out.println(str);
    }
}
