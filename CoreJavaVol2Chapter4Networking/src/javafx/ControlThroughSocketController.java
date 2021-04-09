package javafx;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class ControlThroughSocketController {

    @FXML
    private Pane root;

    public Circle getCircle() {
        return circle;
    }

    @FXML
    private Circle circle;

}
