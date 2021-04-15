package multi_client_multi_textfield;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements  Runnable{
    private GridPane root;
    private Socket incommingSocket;
    private int rowIndex;
    BufferedReader in;
    TextField tf;
    String temp;

    public ClientHandler(GridPane root, Socket incommingSocket, int rowIndex) throws IOException {
        this.root = root;
        this.incommingSocket = incommingSocket;
        this.rowIndex = rowIndex;
        in =  new BufferedReader(new InputStreamReader(incommingSocket.getInputStream()));
        tf = new TextField("from client " + rowIndex);
        temp = "";
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = incommingSocket.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            Platform.runLater(() -> {
                root.add(tf, 1, rowIndex);
            });

            while (true) {

                temp = tf.getText() + " " + in.readLine();
                Platform.runLater(() -> {
                    tf.setText(temp);
                });
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
