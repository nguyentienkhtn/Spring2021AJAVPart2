package server_client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ThreadEchoHandler implements Runnable {
    private Socket in;
    public ThreadEchoHandler(Socket incoming) {
        in = incoming;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = in.getInputStream();
            OutputStream outputStream = in.getOutputStream();
            try(Scanner sc = new Scanner(inputStream, "UTF-8");
                PrintWriter outPrint = new PrintWriter(new OutputStreamWriter(outputStream,"UTF-8"),true))
            {
                outPrint.println("hello type BYE to exit");
                boolean done = false;
                while(!done && sc.hasNext())
                {
                    String mes = sc.nextLine();
                    outPrint.println(mes);
                    if(mes.trim().equals("BYE"))
                        done = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
