package multi_client_multi_textfield;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class ServerGUIController {

    @FXML
    private GridPane root;

    @FXML
    private Label noOfConnections;

    public Label getNoOfConnections() {
        return noOfConnections;
    }

    public GridPane  getRoot() {
        return root;
    }
}
