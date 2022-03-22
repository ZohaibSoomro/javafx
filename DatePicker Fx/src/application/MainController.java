package application;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class MainController {
	@FXML
	private DatePicker dp;
	
	@FXML
	private Label showDate;
	
	public void showDate(ActionEvent event) {
		LocalDate lDate=dp.getValue();
		showDate.setText(lDate.toString());
	}
	;
}
