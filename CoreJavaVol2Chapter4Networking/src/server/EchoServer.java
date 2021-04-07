package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try(ServerSocket s = new ServerSocket(8189))
        {
            try(Socket incoming = s.accept())
            {
                InputStream in = incoming.getInputStream();
                OutputStream out = incoming.getOutputStream();
                try (Scanner scanner = new Scanner(in,"UTF-8")){
                    PrintWriter outPrinter = new PrintWriter(new OutputStreamWriter(out, "UTF-8"),true);
                    outPrinter.println("Hello! Type BYE to exit!");
                    boolean done = false;
                    while(!done && scanner.hasNext())
                    {
                        String inMes = scanner.nextLine();
                        System.out.println("Client: " + inMes);
                        outPrinter.println("Echo:" + inMes);
                        if(inMes.trim().equals("BYE"))
                            done = true;
                    }
                }
            }
        }
    }
}
