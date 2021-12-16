package cn.fantasticmao.demo.java.lang.io.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UdpDemo
 *
 * @author fantasticmao
 * @since 2019/2/13
 */
public class UdpDemo {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName("192.168.0.1");
        byte[] bytes = "maomao test".getBytes();
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, inetAddress, 9999);
        socket.send(packet);
    }
}
