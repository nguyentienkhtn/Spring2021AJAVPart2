package javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        View view = new View();
        primaryStage = view.getStage();
        primaryStage.setOnShowing(e -> new Thread(new SocketListening()).start());
        primaryStage.show();
    }

    public class SocketListening implements Runnable
    {

        @Override
        public void run() {
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
            catch (IOException e)
            {
                System.out.println("interupted!");
            }

        }
    }

    public class View
    {
        private Stage stage;
        Pane root = null;

        public View() {
            stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("ControlThroughSocket.fxml"));
            ControlThroughSocketController controlThroughSocketController = new ControlThroughSocketController();
            fxmlLoader.setController(controlThroughSocketController);
//            Pane root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene sc = new Scene(root);
            stage.setScene(sc);


        }

        public Pane getRoot() {
            return root;
        }

        public Stage getStage() {
            return stage;
        }
    }

}
