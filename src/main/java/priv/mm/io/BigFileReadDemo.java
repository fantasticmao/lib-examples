package priv.mm.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

/**
 * BigFileReadDemo
 *
 * @author maodh
 * @since 2018/12/17
 */
public class BigFileReadDemo {
    private File bigFile;

    public BigFileReadDemo(String path) {
        bigFile = Paths.get(path).toFile();
    }

    private void useBufferedInputStream() {
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

    private void useNioChannel() {
        final int bufferSize = 100 * 1024;
        try (FileChannel fileChannel = FileChannel.open(bigFile.toPath())) {
            byte[] bytes = new byte[bufferSize];
            ByteBuffer byteBuffer = ByteBuffer.allocate(bufferSize);
            for (long position = 0, totalSize = fileChannel.size(); position <= totalSize; position += bufferSize) {
                if ((position + bufferSize) > totalSize) { // 最后一次读取
                    final int lastSize = (int) (totalSize - position);
                    byteBuffer = ByteBuffer.allocate(lastSize);
                    bytes = new byte[lastSize];
                }
                if (fileChannel.read(byteBuffer) != -1) {
                    byteBuffer.flip();
                    byteBuffer.get(bytes);
                    byteBuffer.clear();
                    // 处理 byte[]
                } else {
                    throw new RuntimeException("read big file error");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // FIXME
    private void useMemoryMappedFile() {
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

    public static void main(String[] args) {
        BigFileReadDemo demo = new BigFileReadDemo("/var/log/bbs/bbs.log");

        new Thread(() -> {
            long start = System.nanoTime();
            demo.useBufferedInputStream();
            long end = System.nanoTime();
            System.out.println("BufferedInputStream cost " + (end - start) / Math.pow(10, 9) + " second");
        }).start();

        new Thread(() -> {
            long start = System.nanoTime();
            demo.useNioChannel();
            long end = System.nanoTime();
            System.out.println("NIO Channel cost " + (end - start) / Math.pow(10, 9) + " second");
        }).start();

        new Thread(() -> {
            long start = System.nanoTime();
            //demo.useMemoryMappedFile();
            long end = System.nanoTime();
            System.out.println("MemoryMappedFile cost " + (end - start) / Math.pow(10, 9) + " second");
        }).start();
    }
}
