package priv.mm.design_pattern.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * Dispatcher
 *
 * @author maodh
 * @since 24/02/2018
 */
public class Dispatcher {
    private final Selector selector;
    private String data = null; // 持久化请求数据。这种持久化方式是线程不安全的，但演示代码没所谓

    public Dispatcher(Selector selector) {
        this.selector = selector;
    }

    public void dispatch(SelectionKey key) throws IOException {
        // a connection was accepted by a ServerSocketChannel.
        if (key.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel clientSocketChannel = serverSocketChannel.accept();
            clientSocketChannel.configureBlocking(false);

            clientSocketChannel.register(selector, SelectionKey.OP_READ); // 接收连接之后，监听「读」事件
        }

        // a connection was established with a remote server.
        if (key.isConnectable()) {
        }

        // a channel is ready for reading
        if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int num = socketChannel.read(buffer);
            if (num > 0) {
                buffer.flip();
                byte[] dataByte = new byte[buffer.limit()];
                buffer.get(dataByte);
                data = new String(dataByte, StandardCharsets.UTF_8);
                System.out.println(data);

                socketChannel.register(selector, SelectionKey.OP_WRITE); // 读取数据之后，监听「写」事件
            } else if (num == -1) {
                socketChannel.close();
            }
        }

        // a channel is ready for writing
        if (key.isWritable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.wrap(("Echo: " + data).getBytes());
            socketChannel.write(buffer);

            socketChannel.register(selector, SelectionKey.OP_READ); // 写入数据之后，再次监听「读」事件
        }
    }
}
