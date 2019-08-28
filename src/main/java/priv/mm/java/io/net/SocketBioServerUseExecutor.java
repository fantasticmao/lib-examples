package priv.mm.java.io.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SocketBioServerUseExecutor
 *
 * @author maodh
 * @since 2019/1/7
 */
public class SocketBioServerUseExecutor {

    public static void main(String[] args) throws IOException {
        final int port = 9999;
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept(); // 线程处于阻塞状态，等待接收连接
                Runnable handler = new SocketBioServerUseMultipleThread.ThreadEchoHandler(socket);
                executorService.submit(handler);
            }
        }
    }
}