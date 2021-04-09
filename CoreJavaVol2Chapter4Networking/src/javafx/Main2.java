package javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Main2 extends Application {
    View view = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        view = new View();
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
                    Platform.runLater(() -> {
                        try {
                            InputStream inputStream = incoming.getInputStream();
                            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                            Coordinate newCoordinate = (Coordinate)objectInputStream.readObject();

                            view.getCircle().setCenterX(newCoordinate.getX());
                            view.getCircle().setCenterY(newCoordinate.getY());



                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
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

    public class View
    {
        private Stage stage;
        Pane root = null;
        Circle circle = null;

        public View() {
            stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("ControlThroughSocket.fxml"));
            ControlThroughSocketController controlThroughSocketController = new ControlThroughSocketController();
            fxmlLoader.setController(controlThroughSocketController);
            circle = controlThroughSocketController.getCircle();
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

        public Circle getCircle() {
            return  circle;
        }
    }

}
