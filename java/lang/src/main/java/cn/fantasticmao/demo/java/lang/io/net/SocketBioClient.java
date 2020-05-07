package cn.fantasticmao.demo.java.lang.io.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * SocketBioClient
 * 服务器发送给服务器输出流的所有信息都会成为客户端程序的输入，
 * 同时来自客户端程序的所有输出都会被包含在服务器输入流中。
 *
 * @author maodh
 * @since 2017/9/4
 */
public class SocketBioClient {

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket()) {
            //socket.connect(new InetSocketAddress("time-a.nist.gov", 13), 5_000);
            socket.connect(new InetSocketAddress("localhost", 9999), 5_000);
            try (InputStream inputStream = socket.getInputStream();
                 InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                System.out.println(bufferedReader.readLine());
            }
        }
    }
}
