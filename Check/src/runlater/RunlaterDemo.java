package runlater;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class RunlaterDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("RunlaterFXML.fxml"));

        Pane root = loader.load();
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.show();


    }
}
