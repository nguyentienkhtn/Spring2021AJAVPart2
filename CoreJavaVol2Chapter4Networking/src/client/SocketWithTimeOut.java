package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketWithTimeOut {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket();
        s.connect(new InetSocketAddress("time-a.nist.gov", 13), 1500);
        try(Scanner in = new Scanner(s.getInputStream(), "UTF-8"))
        {
            while(in.hasNext())
                System.out.println(in.nextLine());
        }

    }
}
