package application;

import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.bind.Binder;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

public class MainController implements Initializable {

	final MyNumber number = new MyNumber();
	@FXML
	private Label lblstatus;

	@FXML
	private ProgressBar pBar;

	@FXML
	private ProgressIndicator pInd;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		number.setNumber(0);
		number.numberProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
				lblstatus.setText(new Double(number.getNumber()).toString());

			}
		});
		pBar.progressProperty().bind(number.numberProperty());
		pInd.progressProperty().bind(number.numberProperty());
	}

	public void btnClicked(ActionEvent event) {
		number.setNumber(number.getNumber() + 0.1);
	}

	public void btn2Clicked(ActionEvent event) {
		number.setNumber(number.getNumber() - 0.1);
	}

}
