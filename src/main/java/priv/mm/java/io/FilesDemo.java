package priv.mm.java.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * FilesDemo
 *
 * @author maodh
 * @since 07/02/2018
 */
public class FilesDemo {
    private final String classPath = FilesDemo.class.getResource("/").getPath();
    private final Path path = Paths.get(classPath, "声声慢.txt");

    /**
     * @see Files#readAllBytes(Path)
     * @see Files#readAllLines(Path)
     */
    private void read() {
        try {
            byte[] bytes = Files.readAllBytes(path);
            System.out.println(new String(bytes, StandardCharsets.UTF_8));
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            System.out.println(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see Files#createDirectory(Path, FileAttribute[])
     * @see Files#delete(Path)
     */
    private void createAndDelete() {
        Path newPath = Paths.get(classPath + "new");
        try {
            Path d = Files.createDirectory(newPath);
            TimeUnit.SECONDS.sleep(5);
            Files.deleteIfExists(d);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see Files#copy(Path, OutputStream)
     * @see Files#move(Path, Path, CopyOption...)
     */
    private void copyAndMove() {
        // 略
    }

    public static void main(String[] args) throws IOException {
        FilesDemo demo = new FilesDemo();
        demo.createAndDelete();
    }
}
