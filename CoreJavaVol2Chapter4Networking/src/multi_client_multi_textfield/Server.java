package multi_client_multi_textfield;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends Application {
    int numberOfConnections = 0;
    int connectionCount = 0;
    ServerGUIController serverGUIController = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("ServerGUI.fxml"));
        serverGUIController = new ServerGUIController();
        fxmlLoader.setController(serverGUIController);
        GridPane root = fxmlLoader.load();
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setOnShowing(e -> new Thread(new ServerListener()).start());
        primaryStage.setTitle("Server screen");
        primaryStage.show();

    }
    class ServerListener implements Runnable{

        @Override
        public void run() {

            try (ServerSocket socket = new ServerSocket(8189)){

                while (true)
                {
                    Socket incoming = socket.accept();
                    connectionCount ++;
                    new Thread(new ClientHandler(serverGUIController.getRoot(), incoming, connectionCount)).start();


                }

            }
            catch (IOException e)
            {
                System.out.println("interupted!");
            }

        }
    }

}

