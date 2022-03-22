package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class GraphController {
	@FXML
	LineChart<String, Number> lineChart;
	String attended = "",total="";

	
	public void actionBtn(ActionEvent event) {
		lineChart.getData().clear();
		XYChart.Series<String, Number> series = new Series<String, Number>();
		series.getData().add(new XYChart.Data<String, Number>("t1", 0));
		//eries.getData().add(new XYChart.Data<String, Number>("t2", AttendanceInformation.percentage));
		lineChart.getData().add(series);
	}
}
