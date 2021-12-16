package cn.fantasticmao.demo.java.lang.io;

import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

/**
 * BigFileReadTest
 *
 * @author fantasticmao
 * @since 2020-05-14
 */
@Ignore
public class BigFileReadTest {

    // TODO 生成大文件
    private File bigFile = Paths.get("").toFile();

    @Test
    public void useBufferedInputStream() {
        final int bufferSize = 100 * 1024;
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(bigFile), bufferSize)) {
            byte[] bytes = new byte[bufferSize];
            while (in.read(bytes) != -1) {
                // 处理 byte[]
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void useNioChannel() {
        final int bufferSize = 100 * 1024;
        try (FileChannel fileChannel = FileChannel.open(bigFile.toPath())) {
            byte[] bytes = new byte[bufferSize];
            ByteBuffer byteBuffer = ByteBuffer.allocate(bufferSize);
            while (fileChannel.position() < fileChannel.size()) {
                if ((fileChannel.position() + bufferSize) > fileChannel.size()) { // 最后一次读取
                    final int lastSize = (int) (fileChannel.size() - fileChannel.position());
                    byteBuffer = ByteBuffer.allocate(lastSize);
                    bytes = new byte[lastSize];
                }
                if (fileChannel.read(byteBuffer) != -1) {
                    byteBuffer.flip();
                    byteBuffer.get(bytes);
                    byteBuffer.clear();
                    // 处理 byte[]
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // FIXME
    @Test
    public void useMemoryMappedFile() {
        final int bufferSize = 100 * 1024;
        try (FileChannel fileChannel = FileChannel.open(bigFile.toPath())) {
            for (long position = 0, totalSize = fileChannel.size(); position <= totalSize; position += bufferSize) {
                final int nextSize = (position + bufferSize) > totalSize ? (int) (totalSize - position) : bufferSize;
                MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, position, nextSize);
                byte[] bytes = new byte[nextSize];
                mappedByteBuffer.get(bytes);
                // 处理 byte[]
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
