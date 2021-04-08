package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketTest {
    public static void main(String[] args) {
//        "time-a.nist.gov", 13
        try (Socket s = new Socket("time-a.nist.gov", 10)){
            Scanner in = new Scanner(s.getInputStream(), "UTF-8");
            while(in.hasNext())
                System.out.println(in.nextLine());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
