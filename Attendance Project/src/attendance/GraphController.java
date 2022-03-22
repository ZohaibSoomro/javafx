package attendance;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;

public class GraphController implements Initializable {
	@FXML
	LineChart<String, Number> lineChart;
	@FXML
	Label totLectureLbl, attendanceLbl, presentLbl;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//lineChart.getData().clear();
		lineChart.setTitle("ATTENDANCE GRAPH OF 19SW" + GraphClass.rollNum);
		XYChart.Series<String, Number> series = new Series<String, Number>();
		series.getData().add(new XYChart.Data<String, Number>("t1", 0));
		series.getData().add(new XYChart.Data<String, Number>("t2", GraphClass.percentage));
		attendanceLbl.setText(attendanceLbl.getText() + GraphClass.percentage + "%");
		presentLbl.setText(presentLbl.getText() + GraphClass.present);
		totLectureLbl.setText(totLectureLbl.getText() + GraphClass.total);
		lineChart.getData().add(series);

	}
}
