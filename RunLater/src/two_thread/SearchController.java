package two_thread;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SearchController {

    @FXML
    private Pane root;

    @FXML
    private Button thread1SearchButton;

    @FXML
    private Label thread1Result;

    @FXML
    private TextField searchDirThread1;

    @FXML
    private TextField keywordThread1;

    @FXML
    private Button thread2SearchButton;

    @FXML
    private Label thread2Result;

    @FXML
    private TextField searchDirThread2;

    @FXML
    private TextField keywordThread2;

    @FXML
    void thread1SearchButtonAction(ActionEvent event) {


    }

    @FXML
    void thread2SearchButtonAction(ActionEvent event) {

    }

}
