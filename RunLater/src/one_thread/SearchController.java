package one_thread;
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
    void thread1SearchButtonAction(ActionEvent e){
        thread1Result.setText("Searching");
//        Runnable task = () -> {
//          try {

        Platform.runLater(() ->{

            System.out.println("thread 1 running");
            thread1SearchButton.setDisable(true);
            try (Scanner sc = new Scanner(new File(searchDirThread1.getText()));) {

                Thread.sleep(20);

                while(sc.hasNext()) {
                    String line = sc.nextLine();
                    System.out.println(line);
                    if(line.contains(keywordThread1.getText()))
                        thread1Result.setText("Found!!!!");
                }
                thread1SearchButton.setDisable(false);

            } catch (Exception ev) {
                ev.printStackTrace();
            }

        });
    }

    @FXML
    void thread2SearchButtonAction(ActionEvent event) {

        thread2Result.setText("Searching");
//        Runnable task = () -> {
//          try {

              Platform.runLater(() ->{

                  System.out.println("thread 2 running");
                  thread2SearchButton.setDisable(true);
                  try (Scanner sc = new Scanner(new File(searchDirThread2.getText()));) {

                      Thread.sleep(20);

                      while(sc.hasNext()) {
                          String line = sc.nextLine();
                          System.out.println(line);
                          if(line.contains(keywordThread2.getText()))
                              thread2Result.setText("Found!!!!");
                      }
                      thread2SearchButton.setDisable(false);

                  } catch (Exception e) {
                      e.printStackTrace();
                  }

              });
          }
//          catch (InterruptedException e) {
//              e.printStackTrace();
//          }
//        };
//        new Thread(task).start();

    }


