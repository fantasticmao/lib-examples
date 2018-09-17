package priv.mm.io.net;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * SocketTest
 *
 * @author maodh
 * @since 15/02/2018
 */
public class SocketTest {

    public static void main(String[] args) {
        try (Socket socket = new Socket()) {
            InetSocketAddress address = new InetSocketAddress("time-a.nist.gov", 13);
            socket.connect(address, 5_000);
            socket.setSoTimeout(5_000);
            try (Scanner scanner = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8.name())) {
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
            }
        } catch (ConnectException e) {
            System.out.println("连接超时...");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            System.out.println("读写超时...");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
