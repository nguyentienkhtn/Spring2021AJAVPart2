package server_client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class ClientServer {
    Runnable server = () ->{
        try(ServerSocket s = new ServerSocket(8189))
        {
            while (true)
            {
                Socket incomming = s.accept();
                Runnable r = new ThreadEchoHandler(incomming);
                new Thread(r).start();

            }
        }
       catch (IOException e) {
            e.printStackTrace();
        }
    };

    Runnable client = () ->
    {

    };
}
