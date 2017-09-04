package priv.mm.net;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * SocketClient
 *
 * @author maodh
 * @since 2017/9/4
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 8080);
        PrintStream out = new PrintStream(client.getOutputStream());
        out.print("hello world!");
        out.close();
        client.close();
    }
}
