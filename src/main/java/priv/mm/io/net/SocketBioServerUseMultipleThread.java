package priv.mm.io.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * SocketBioServerUseMultipleThread
 *
 * @author maodh
 * @see <a href="https://javadoop.com/post/java-nio">Java NIO：Buffer、Channel 和 Selector </a>
 * @see <a href="https://javadoop.com/post/nio-and-aio">Java 非阻塞 IO 和异步 IO</a>
 * @see <a href="https://www.ibm.com/developerworks/java/library/j-javaio/">多路复用 IO</a>
 * @see <a href="https://www.ibm.com/developerworks/cn/java/j-lo-io-optimize/index.html">概念：同步阻塞IO、同步非阻塞IO、异步非阻塞IO</a>
 * @since 2017/9/4
 */
public class SocketBioServerUseMultipleThread {

    public static void main(String[] args) throws IOException {
        final int port = 9999;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept(); // 线程处于阻塞状态，等待接收连接
                // 此处可以使用线程池方式优化
                Runnable handler = new ThreadEchoHandler(socket);
                new Thread(handler).start();
            }
        }
    }

    public static class ThreadEchoHandler implements Runnable, Closeable {
        private Socket socket;

        public ThreadEchoHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void close() {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try (OutputStream out = socket.getOutputStream();
                 PrintWriter writer = new PrintWriter(out)) {
                writer.print("hello client\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                this.close();
            }
        }
    }
}
