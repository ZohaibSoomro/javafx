 package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MainController {
	
	public void closeApp(ActionEvent event) {
		Platform.exit();
		System.exit(0);
	}
}
