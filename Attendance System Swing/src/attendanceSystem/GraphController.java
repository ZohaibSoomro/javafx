package attendanceSystem;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

public class GraphController implements Initializable {
	@FXML
	LineChart<String, Number> lineChart;
	@FXML
	Label totLectureLbl, attendanceLbl, presentLbl, suggestionLbl;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// lineChart.getData().clear();
		if (GraphClass.rollNum.length() == 1)
			lineChart.setTitle("ATTENDANCE GRAPH OF 19SW0" + GraphClass.rollNum);
		else
			lineChart.setTitle("ATTENDANCE GRAPH OF 19SW" + GraphClass.rollNum);
		XYChart.Series<String, Number> series = new Series<String, Number>();
		series.getData().add(new XYChart.Data<String, Number>("t1", 0));
		series.getData().add(new XYChart.Data<String, Number>("t2", GraphClass.percentage));

		if (GraphClass.percentage < 75) {
			attendanceLbl.setTextFill(Paint.valueOf("#ff0000")); // red color
			attendanceLbl.setText("*" + attendanceLbl.getText() + GraphClass.percentage + "%");
			int lectures = (int) Math.round((GraphClass.percentage * GraphClass.total) / 100);
			int minrequirement = (int) Math.ceil(0.75 * GraphClass.total);
			suggestionLbl.setText("*You should attend " + (minrequirement - lectures)
					+ " more lectures to get minimum required attendance(i.e 75%).");
		} else {
			attendanceLbl.setText(attendanceLbl.getText() + GraphClass.percentage + "%");
		}
		presentLbl.setText(presentLbl.getText() + GraphClass.present);
		totLectureLbl.setText(totLectureLbl.getText() + GraphClass.total);
		lineChart.getData().add(series);

	}
}
