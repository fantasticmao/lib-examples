package priv.mm.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * SocketServer
 *
 * @author maodh
 * @since 2017/9/4
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(9999);
             Socket socket = serverSocket.accept();
             InputStream in = socket.getInputStream();
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
        }
    }
}
