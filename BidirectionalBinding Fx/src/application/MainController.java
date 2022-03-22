package application;

import java.awt.TextArea;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class MainController implements Initializable {

	
	
	@FXML
	private Slider slider;


	private static final double INIT_VAl = 50;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		slider.setValue(INIT_VAl);
		//field.setText(new Double(INIT_VAl).toString());

		//field.textProperty().bindBidirectional(slider.valueProperty(), NumberFormat.getNumberInstance());

	}

}
