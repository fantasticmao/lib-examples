package cn.fantasticmao.demo.java.lang.io.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * MemcachedClient
 *
 * @author fantasticmao
 * @since 2019/1/5
 */
public class MemcachedClient implements Closeable {
    private Socket socket;
    private InputStream in;
    private OutputStream out;

    private static final String STORED = "STORED";

    public MemcachedClient(String host, int port) throws IOException {
        socket = new Socket(host, port);
        in = socket.getInputStream();
        out = socket.getOutputStream();
    }

    @Override
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    private byte[] get(final String key) throws IOException {
        out.write(String.format("get %s\r\n", key).getBytes());
        out.flush();
        byte[] data = new byte[in.available()];
        if (in.read(data) != -1) {
            return data;
        }
        return new byte[0];
    }

    private boolean set(final String key, final int exp, final byte[] value) throws IOException {
        out.write(String.format("set %s 0 %d %d\r\n", key, exp, value.length).getBytes());
        out.write(value);
        out.write("\r\n".getBytes());
        out.flush();
        byte[] data = new byte[STORED.length()];
        if (in.read(data) != -1) {
            String response = new String(data);
            return STORED.equals(response);
        }
        return false;
    }

    public static void main(String[] args) {
        final String key = "key";
        final byte[] value = "mao".getBytes();

        try (MemcachedClient client = new MemcachedClient("localhost", 11211)) {
            boolean b = client.set(key, 30, value);
            System.out.println(b);
            byte[] data = client.get(key);
            System.out.println(new String(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
