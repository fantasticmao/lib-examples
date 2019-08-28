package priv.mm.java.io.net;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * SocketAioClient
 *
 * @author maodh
 * @since 2019/1/18
 */
public class SocketAioClient implements Closeable {
    private AsynchronousSocketChannel asynchronousSocketChannel;

    public SocketAioClient(String hostname, int port) throws IOException, ExecutionException, InterruptedException {
        asynchronousSocketChannel = AsynchronousSocketChannel.open();
        Future<Void> future = asynchronousSocketChannel.connect(new InetSocketAddress(hostname, port));
        System.out.println("Socket 正在连接...");
        while (!future.isDone() && !Thread.currentThread().isInterrupted()) {
            System.out.println("Socket 正在连接......");
        }
        future.get();
        System.out.println("Socket 连接成功");
    }

    @Override
    public void close() throws IOException {
        asynchronousSocketChannel.close();
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (SocketAioClient client = new SocketAioClient("localhost", 9999)) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(64);
            client.asynchronousSocketChannel.read(buffer, null, new CompletionHandler<Integer, Object>() {
                @Override
                public void completed(Integer result, Object attachment) {
                    buffer.flip();
                    byte[] dataByte = new byte[buffer.limit()];
                    buffer.get(dataByte);
                    buffer.clear();
                    System.out.println(new String(dataByte).trim());
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("读取数据失败");
                }
            });
        }
    }
}
