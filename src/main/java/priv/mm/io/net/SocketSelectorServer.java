package priv.mm.io.net;

import priv.mm.design_pattern.reactor.Dispatcher;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * SocketSelectorServer
 * 多路复用（事件驱动） IO
 *
 * @author maodh
 * @since 23/02/2018
 */
public class SocketSelectorServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocketChannel channel = ServerSocketChannel.open()) {
            channel.configureBlocking(false); // 设置 channel 为非阻塞
            channel.bind(new InetSocketAddress(9999)); // 设置监听端口

            try (Selector selector = Selector.open()) {
                Dispatcher dispatcher = new Dispatcher(selector);

                channel.register(selector, SelectionKey.OP_ACCEPT); // 注册事件
                while (true) {
                    selector.select(); // 阻塞

                    Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeySet.iterator();

                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        dispatcher.dispatch(key);
                    }
                }
            }
        }
    }
}
