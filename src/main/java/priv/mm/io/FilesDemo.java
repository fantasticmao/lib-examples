package priv.mm.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * FilesDemo
 *
 * @author maodh
 * @since 07/02/2018
 */
public class FilesDemo {

    public static void main(String[] args) throws IOException {
        String classPath = FilesDemo.class.getResource("/").getPath();
        Path path = Paths.get(classPath, "声声慢.txt");
        byte[] bytes = Files.readAllBytes(path);
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }
}
