package runlater;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.Scanner;

public class SearchController {

    @FXML
    private Pane root;

    @FXML
    private Button lb;

    @FXML
    private TextField dirInput;

    @FXML
    private TextField keywordInput;

    @FXML
    void searchButtonAction(ActionEvent event) {
        Runnable task = () -> {
          try {
              Thread.sleep(20);
              Platform.runLater(() ->{
                  Scanner sc = new Scanner(dirInput.getText());
                  while(sc.hasNext())
                      if(sc.nextLine().contains(keywordInput.getText()))
                          lb.setText("Found!!!!");
              });
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
        };
        new Thread(task).start();

    }

}
