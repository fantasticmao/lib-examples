package cn.fantasticmao.demo.java.lang.io.net;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * SocketBioServerUseSingleThread
 *
 * @author fantasticmao
 * @since 2019/1/7
 */
public class SocketBioServerUseSingleThread {

    public static void main(String[] args) throws IOException {
        final int port = 9999;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept(); // 线程处于阻塞状态，等待接收连接
                try (OutputStream out = socket.getOutputStream();
                     PrintWriter writer = new PrintWriter(out)) {
                    writer.print("hello client\r\n");
                }
            }
        }
    }
}