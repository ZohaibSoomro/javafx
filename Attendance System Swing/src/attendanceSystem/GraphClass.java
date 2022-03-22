package attendanceSystem;

import java.math.BigDecimal;
import javafx.application.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class GraphClass extends Application {
	static String rollNum = "";
	static double percentage;
	static int present, total;

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Graph.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/sourceImages/Icon-school.png")));
			if (rollNum.length() == 1)
				primaryStage.setTitle("ATTENDANCE GRAPH OF 19SW0" + rollNum);
			else
				primaryStage.setTitle("Attendance Graph Of 19SW" + rollNum);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent arg0) {
					Platform.exit();
					System.exit(0);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		rollNum = args[0];
		BigDecimal bDecimal = new BigDecimal(Float.parseFloat(args[1])); // for truncating to 2 decimal places
		bDecimal = bDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		percentage = bDecimal.doubleValue();
		present = (int) Float.parseFloat(args[2]);
		total = (int) Float.parseFloat(args[3]);
		launch(args);
	}
}
