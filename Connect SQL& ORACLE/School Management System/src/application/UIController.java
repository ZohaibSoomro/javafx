package application;

import java.net.URL;
import java.sql.*;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.*;
import net.proteanit.sql.DbUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class UIController {

	public void stdInfoAction() {
		Connection connection = ConnectToOracle.getORCLConnection();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/StudentInfo.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("School Management System");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void exitAction(ActionEvent event) {
		int result = JOptionPane.showConfirmDialog(null, "Are you sure?");
		if (result == JOptionPane.YES_OPTION)
			System.exit(0);
	}

}
