package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	public LoginModel loginmodel = new LoginModel();

	@FXML
	private Label isConnected;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if (loginmodel.isDbConnected()) {
			isConnected.setText("Connected");
		} else {
			isConnected.setText("Not Connected");
		}

	}

	public void login(ActionEvent event) throws IOException {
		String userString = username.getText();
		String passString = password.getText();

		try {
			boolean isCorrect = loginmodel.isLogin(userString, passString);
			if (isCorrect) {
				isConnected.setText("Username & password is correct");
				
				((Node)event.getSource()).getScene().getWindow().hide();
				///2nd window opened
				Stage primaryStage=new Stage();
				FXMLLoader loader=new FXMLLoader();
				Pane root= loader.load(getClass().getResource("/application/User.fxml").openStream());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} 
			
			else {
				isConnected.setText("Username & password is not correct");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isConnected.setText("Username & password is not correct");
			e.printStackTrace();
		}
	}

}
