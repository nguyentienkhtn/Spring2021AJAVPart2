package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiConnectionServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket socket = new ServerSocket(8189)){
            int connectionCount = 0;
            while (true)
            {
                Socket incoming = socket.accept();
                connectionCount ++;
                System.out.println("Connection count: " + connectionCount);
                Runnable conn = new ThreadEchoHandler(incoming);
                new Thread(conn).start();

            }

        }
    }
}
