package priv.mm.io.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * SocketThreadServer
 *
 * @author maodh
 * @see <a href="https://javadoop.com/post/java-nio">Java NIO：Buffer、Channel 和 Selector </a>
 * @see <a href="https://javadoop.com/post/nio-and-aio">Java 非阻塞 IO 和异步 IO</a>
 * @see <a href="https://www.ibm.com/developerworks/java/library/j-javaio/">多路复用 IO</a>
 * @see <a href="https://www.ibm.com/developerworks/cn/java/j-lo-io-optimize/index.html">概念：同步阻塞IO、同步非阻塞IO、异步非阻塞IO</a>
 * @since 2017/9/4
 */
public class SocketThreadServer {

    private static class ThreadEchoHandler implements Runnable {
        private Socket socket;

        public ThreadEchoHandler(Socket socket) {
            this.socket = socket;
        }

        private void close(Closeable... closeables) {
            for (Closeable closeable : closeables) {
                try {
                    if (closeable != null)
                        closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            try (InputStream in = socket.getInputStream();
                 OutputStream out = socket.getOutputStream();
                 Scanner scanner = new Scanner(in, StandardCharsets.UTF_8.name());
                 PrintWriter writer = new PrintWriter(
                         new OutputStreamWriter(out, StandardCharsets.UTF_8), true)) {
                writer.println("Hello, MaoMao!");
                String line;
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    if ("bye".equals(line.trim().toLowerCase())) break;
                    System.out.println(line);
                    writer.println("Echo: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close(socket);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            while (true) {
                Socket socket = serverSocket.accept(); // socket 在子线程中关闭
                // 此处可以使用线程池方式优化
                Runnable handler = new ThreadEchoHandler(socket);
                Thread thread = new Thread(handler);
                thread.start();
            }
        }
    }
}
