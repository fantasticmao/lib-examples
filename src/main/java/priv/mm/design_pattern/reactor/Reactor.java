package priv.mm.design_pattern.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Reactor
 *
 * @author maodh
 * @since 23/02/2018
 */
public class Reactor implements Runnable {
    private final Selector selector;
    private final Dispatcher dispatcher;

    Reactor(final int port) throws IOException {
        selector = Selector.open();
        dispatcher = new Dispatcher(selector);

        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false); // 设置 channel 为非阻塞
        channel.bind(new InetSocketAddress(port)); // 设置监听端口
        channel.register(selector, SelectionKey.OP_ACCEPT); // 注册事件
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                int num = selector.select(); // 阻塞等待事件发生
                if (num <= 0) continue;

                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    dispatcher.dispatch(it.next());
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Reactor reactor = new Reactor(9999);
        Thread thread = new Thread(reactor);
        thread.start();
    }
}
