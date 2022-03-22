package application;

import java.awt.Window;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MainController implements Initializable {

	@FXML
	private WebView webView;
	private WebEngine engine;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		engine = webView.getEngine();

	}

	public void btn1(ActionEvent event) {
		engine.load("https://google.com");
	}

	public void btn2(ActionEvent event) {
		engine.executeScript("window.location=\"https://easylineconverter.com//\";");
	}

	public void btn3(ActionEvent event) {
		engine.loadContent("<html> <body><h1> Hello World!</h1> </body> </html>");
	}

	public void btn4(ActionEvent event) {
		engine.reload();
		
	}

}
