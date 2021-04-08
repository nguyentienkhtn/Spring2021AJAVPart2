package javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        View view = new View();
        primaryStage = view.getStage();
        primaryStage.show();
    }
    public class View
    {
        private Stage stage;

        public View() {
            stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("ControlThroughSocket.fxml"));
            ControlThroughSocketController controlThroughSocketController = new ControlThroughSocketController();
            fxmlLoader.setController(controlThroughSocketController);
            Pane root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene sc = new Scene(root);
            stage.setScene(sc);


        }

        public Stage getStage() {
            return stage;
        }
    }

}
