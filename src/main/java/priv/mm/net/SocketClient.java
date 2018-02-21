package priv.mm.net;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * SocketClient
 * 服务器发送给服务器输出流的所有信息都会成为客户端程序的输入，
 * 同时来自客户端程序的所有输出都会被包含在服务器输入流中。
 *
 * @author maodh
 * @since 2017/9/4
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        try (Socket client = new Socket("localhost", 9999);
             Scanner scanner = new Scanner(client.getInputStream(), StandardCharsets.UTF_8.name())) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
    }
}
