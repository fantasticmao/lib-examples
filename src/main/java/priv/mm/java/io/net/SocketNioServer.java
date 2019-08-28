package priv.mm.java.io.net;

import priv.mm.design_pattern.reactor.Dispatcher;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * SocketNioServer
 * 多路复用（事件驱动） IO
 *
 * @author maodh
 * @since 23/02/2018
 */
public class SocketNioServer implements Closeable {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private Dispatcher dispatcher;

    public SocketNioServer(final int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false); // 设置 channel 为非阻塞
        serverSocketChannel.bind(new InetSocketAddress(port)); // 设置监听端口

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 注册事件

        dispatcher = new Dispatcher(selector);
    }

    @Override
    public void close() throws IOException {
        selector.close();
        serverSocketChannel.close();
    }

    public static void main(String[] args) throws IOException {
        final int port = 9999;
        try (SocketNioServer demo = new SocketNioServer(port)) {
            while (!Thread.currentThread().isInterrupted()) {
                demo.selector.select(); // 线程处于阻塞状态，等待接收连接

                Set<SelectionKey> selectionKeySet = demo.selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    demo.dispatcher.dispatch(key);
                }
            }
        }
    }
}
