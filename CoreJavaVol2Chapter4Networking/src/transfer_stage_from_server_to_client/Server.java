package transfer_stage_from_server_to_client;

import javafx.Coordinate;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Application {
    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("ServerGUI.fxml"));
        ServerGUIController serverGUIController = new ServerGUIController();
        fxmlLoader.setController(serverGUIController);
        Pane root = fxmlLoader.load();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setOnShowing(event ->{
            new Thread(new SocketConnection()).start();
        });
        primaryStage.show();

    }
    public class SocketConnection implements Runnable{

        @Override
        public void run() {
            try (ServerSocket socket = new ServerSocket(8189)){
                int connectionCount = 0;
                while (true)
                {
                    Socket incoming = socket.accept();
                    connectionCount ++;
                    System.out.println("Connection count: " + connectionCount);
                    Platform.runLater(() -> {
                        try {
                            OutputStream outputStream = incoming.getOutputStream();
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                            objectOutputStream.writeObject(getScene());



                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                }

            }
            catch (IOException e)
            {
                System.out.println("interupted!");
            }
        }
    }
    public Scene getScene()
    {
        return scene;
    }
}
