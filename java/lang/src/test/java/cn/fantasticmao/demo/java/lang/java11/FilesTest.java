package cn.fantasticmao.demo.java.lang.java11;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
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
        Path path = Paths.get(this.getClass().getResource("/io/test.txt").getPath());
        String str = Files.readString(path);
        Assert.assertNotNull(str);
        System.out.println(str);
    }
}
