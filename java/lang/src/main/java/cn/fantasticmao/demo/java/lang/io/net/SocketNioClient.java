package cn.fantasticmao.demo.java.lang.io.net;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * SocketNioClient
 *
 * @author fantasticmao
 * @since 2019/1/7
 */
public class SocketNioClient implements Closeable {
    private SocketChannel socketChannel;

    public SocketNioClient(String hostname, int port, boolean block) throws IOException, InterruptedException {
        socketChannel = SocketChannel.open();
        if (block) {
            socketChannel.configureBlocking(true); // 阻塞连接
            boolean isConnect = socketChannel.connect(new InetSocketAddress(hostname, port));
            // 线程处于阻塞状态，直至连接成功
            assert isConnect;
            System.out.println("Socket 连接成功");
        } else {
            socketChannel.configureBlocking(false); // 非阻塞连接
            boolean isConnect = socketChannel.connect(new InetSocketAddress(hostname, port));
            // 线程处于非阻塞状态，程序可以执行其它代码
            System.out.println("Socket 正在连接...");
            if (isConnect) {
                System.out.println("Socket 连接成功！");
            } else {
                while (!Thread.currentThread().isInterrupted()) {
                    TimeUnit.MILLISECONDS.sleep(50); // 线程休眠 50 ms
                    if (socketChannel.finishConnect()) {
                        System.out.println("Socket 连接成功");
                        break;
                    } else {
                        System.out.println("Socket 正在连接......");
                    }
                }
            }
        }
    }

    @Override
    public void close() throws IOException {
        socketChannel.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        try (SocketNioClient client = new SocketNioClient("localhost", 9999, false)) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(64);
            while (client.socketChannel.read(buffer) > 0) {
                buffer.flip();
                byte[] dataByte = new byte[buffer.limit()];
                buffer.get(dataByte);
                buffer.clear();
                System.out.println(new String(dataByte).trim());
            }
        }
    }
}
