package priv.mm.io;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * MemoryMappedFileDemo
 *
 * @author maodh
 * @since 06/02/2018
 */
public class MemoryMappedFileDemo {

    public static void main(String[] args) {
        Path path = Paths.get("/Users/maomao/IdeaProjects/Demo/src/main/java/priv/mm/io/声声慢.txt");
        try (FileChannel channel = FileChannel.open(path)) {
            final long size = channel.size();
            final byte[] bytes = new byte[(int) size];
            MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
            for (int i = 0; i < size; i++) {
                bytes[i] = byteBuffer.get();
            }
            System.out.println(new String(bytes, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
