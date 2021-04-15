package application;
	
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Main extends Application {
	 int countSever = 0;
	 ChattingLayoutController chattingLayoutController;
	 @Override
	public void start(Stage primaryStage) throws IOException {
		 FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("ChattingLayout.fxml"));
		 chattingLayoutController = new ChattingLayoutController();
	        fxmlLoader.setController(chattingLayoutController);
	        GridPane root = fxmlLoader.load();
	        Scene sc = new Scene(root);
	        primaryStage.setScene(sc);
	        primaryStage.setOnShowing(e -> new Thread(new ServerListener()).start());
	        primaryStage.setTitle("Chatting");
	        primaryStage.show();
		
//		Platform.runLater(()->{
//			try(ServerSocket s = new ServerSocket(8189))
//	        {
//				
//	    	 while(true) {
//	    		 Socket incoming = s.accept();
//	         	 countSever++;
//	         	System.out.println(countSever);
//	    		
//	         	Runnable conn = ()->{
//	        		try {
//	        			Thread.sleep(20);
//						InputStream in = incoming.getInputStream();
//		                OutputStream out = incoming.getOutputStream();
//		                Scanner scanner = new Scanner(in,"UTF-8");
//	                    PrintWriter outPrinter = new PrintWriter(new OutputStreamWriter(out, "UTF-8"),true);
//	                    outPrinter.println("Hello! Type BYE to exit!");
//	                    boolean done = false;
//	                    int count  = countSever;
//	                    while(!done && scanner.hasNext())
//	                    {	
//	                    	
//	                    	 String inMes = scanner.nextLine();
//	                    	 ta.appendText(inMes +"\n");;
//	                         System.out.println("Client:"+count+" " + inMes);
//	                         outPrinter.println("Echo:" + inMes);
//	                         if(inMes.trim().equals("BYE")) {
//	                        	  done = true;
//	                        	  scanner.close();
//	                         }
//	                    }
//	        		}catch (InterruptedException | IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//	         	};new Thread(conn).start();
//	    	 }
//	        }
//	        
//
//		 catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		 	}
//		});
//		
	}
	
	
    class ServerListener implements Runnable{
		Scanner scanner;

        @Override
        public void run() {
            try (ServerSocket socket = new ServerSocket(8189)){

                while (true)
                {
                    Socket incoming = socket.accept();
                    System.out.println(incoming.toString());
                    countSever ++;
                    System.out.println("Connection count: " + countSever);
                    Platform.runLater(() -> {
                    	chattingLayoutController.getLabel().setText("Chatting "+Integer.toString(countSever)+" Client");
                        TextArea ta = new TextArea("from client " + countSever);
                       
                        
                        /////////////////
                        try {
                        	System.out.println("gelo");
	                        InputStream in = incoming.getInputStream();
			                OutputStream out = incoming.getOutputStream();
			                System.out.println("gelo1");
			                scanner = new Scanner(in,"UTF-8");
			                System.out.println("gelo2");
		                    PrintWriter outPrinter = new PrintWriter(new OutputStreamWriter(out, "UTF-8"),true);
		                    outPrinter.println("Hello! Type BYE to exit!");
		                    boolean done = false;
		                    int count  = countSever;
		                    if (!scanner.hasNext()) {
								
//								
							}
//		                    while(scanner.hasNext())
//		                    {	
//		                    	 String inMes = scanner.nextLine();
//		                    	 ta.appendText(inMes +"\n");
//		                         System.out.println("Client:"+count+" " + inMes);
//		                         outPrinter.println("Echo:" + inMes);
////		                         if(inMes.trim().equals("BYE")) {
////		                        	//  done = true;
////
////		                         }
//		                    }
                        }catch (IOException e)
	                    {
	                        System.out.println("interupted!");
	                    }
						scanner.close();
	                    /////////////////////
                        if(countSever%2==1) {
                        	chattingLayoutController.getRoot().add(ta, 0,countSever);
                        }else
                        	chattingLayoutController.getRoot().add(ta, 1,countSever);
                    });


                }

            }
            catch (IOException e)
            {
                System.out.println("interupted!");
            }

        }
    }

	public static void main(String[] args) {
		launch(args);
	}
}
