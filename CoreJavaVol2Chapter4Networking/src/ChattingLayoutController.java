import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ChattingLayoutController {
	@FXML
	private GridPane rootId;
	@FXML
	private Label labelId;
	
	public Label getLabel(){
		return labelId;
	}
	public GridPane getRoot(){
		return rootId;
	}

}
