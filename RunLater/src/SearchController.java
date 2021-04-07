import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.Scanner;

public class SearchController {

    @FXML
    private Pane root;

    @FXML
    private Label lb;
    @FXML
    private Button button;

    @FXML
    private TextField dirInput;

    @FXML
    private TextField keywordInput;

    @FXML
    void searchButtonAction(ActionEvent event) {
        lb.setText("Searching");
        Runnable task = () -> {
          try {
              button.setDisable(true);
              Thread.sleep(20);
              Platform.runLater(() ->{
                  try {
                      Scanner sc = new Scanner(new File(dirInput.getText()));
                      while(sc.hasNext()) {
                          String line = sc.nextLine();
                          System.out.println(line);
                          if(line.contains(keywordInput.getText()))
                              lb.setText("Found!!!!");
                      }
                      button.setDisable(false);
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              });
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
        };
        new Thread(task).start();

    }

}
