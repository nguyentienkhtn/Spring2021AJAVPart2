package javafx;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class ClientControlHandler implements Runnable{
    private Socket incommingSocket;

    public ClientControlHandler(Socket incommingSocket) {
        this.incommingSocket = incommingSocket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = incommingSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Map<Integer,Integer> result = (Map)objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
