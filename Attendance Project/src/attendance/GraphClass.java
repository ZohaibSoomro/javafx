package attendance;

import java.math.BigDecimal;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class GraphClass extends Application {
	static String rollNum = "";
	static double percentage;
	static int present, total;
	static Stage stage;

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/attendance/Graph.fxml"));
			Scene scene = new Scene(root);
			stage = primaryStage;
			primaryStage.setScene(scene);
			primaryStage.setTitle("Attendance Graph Of 19SW" + rollNum);
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent arg0) {
					Platform.exit();
					System.exit(0);

				}
			});
			primaryStage.show();

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
