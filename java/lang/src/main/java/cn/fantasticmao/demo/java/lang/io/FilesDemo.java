package cn.fantasticmao.demo.java.lang.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.List;

/**
 * FilesDemo
 *
 * @author maodh
 * @since 07/02/2018
 */
public class FilesDemo {

    /**
     * @see Files#readAllBytes(Path)
     * @see Files#readAllLines(Path)
     */
    @Test
    public void read() throws IOException {
        Path path = Paths.get(FilesDemo.class.getResource("test.txt").getPath());

        byte[] bytes = Files.readAllBytes(path);
        System.out.println("readAllBytes: " + new String(bytes, StandardCharsets.UTF_8));

        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        System.out.println("readAllLines: " + lines);
    }

    /**
     * @see Files#createDirectory(Path, FileAttribute[])
     * @see Files#delete(Path)
     */
    @Test
    public void createAndDelete() throws IOException {
        Path newPath = Paths.get(FilesDemo.class.getResource("").getPath(), "new");
        Path d = Files.createDirectory(newPath);
        Assert.assertTrue(Files.exists(d));
        Files.deleteIfExists(d);
        Assert.assertFalse(Files.exists(d));
    }

    /**
     * @see Files#copy(Path, OutputStream)
     * @see Files#move(Path, Path, CopyOption...)
     */
    @Test
    public void copyAndMove() {
        // 略
    }

}
