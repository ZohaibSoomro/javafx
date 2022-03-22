package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class MainController {

	@FXML
	private RadioButton Rb1;
	
	@FXML
	private RadioButton Rb2;
	
	@FXML
	private Label lbl;
	
	public void radioSelect(ActionEvent event) {
		String message="";
		
		if(Rb1.isSelected()) {
			message+=Rb1.getText()+"\t";
		}
		if(Rb2.isSelected()) {
			message+=Rb2.getText()+"\t";
		}
		lbl.setText(message);
	}
	
}
