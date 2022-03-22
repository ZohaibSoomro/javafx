package application;

import javax.swing.plaf.ToolTipUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

public class MainController {

	@FXML
	private LineChart<String, Number> lineChart;
	@FXML
	private Label label;

	public void btn(ActionEvent event) {
		lineChart.getData().clear();

		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		series.getData().add(new XYChart.Data<String, Number>("Jan", 200));
		series.getData().add(new XYChart.Data<String, Number>("Feb", 500));
		series.getData().add(new XYChart.Data<String, Number>("Mar", 300));
		series.getData().add(new XYChart.Data<String, Number>("Apr", 600));
		series.setName("Month Pay 1");

//		XYChart.Series<String, Number> series2= new XYChart.Series<String, Number>();
//		series2.getData().add(new XYChart.Data<String,Number>("Jan",100));
//		series2.getData().add(	new XYChart.Data<String,Number>("Feb",50));
//		series2.getData().add(	new XYChart.Data<String,Number>("Mar",250));
//		series2.getData().add(new XYChart.Data<String,Number>("Apr",730));
//		series2.getData().add(	new XYChart.Data<String,Number>("May",830));
//		series2.getData().add(	new XYChart.Data<String,Number>("June",650));
//		series2.setName("Month Pay 2");
//		
//		
//		XYChart.Series<String, Number> series3= new XYChart.Series<String, Number>();
//		series3.getData().add(new XYChart.Data<String,Number>("Jan",150));
//		series3.getData().add(	new XYChart.Data<String,Number>("Feb",70));
//		series3.getData().add(	new XYChart.Data<String,Number>("Mar",290));
//		series3.getData().add(new XYChart.Data<String,Number>("Apr",650));
//		series3.getData().add(	new XYChart.Data<String,Number>("May",720));
//		series3.getData().add(	new XYChart.Data<String,Number>("June",790));
//		series3.setName("Month Pay 3");

		lineChart.getData().addAll(series);
//				series2,series3);

		for (final XYChart.Data<String, Number> data : series.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					label.setText(data.getXValue() + data.getYValue());
					System.out.println(data.getXValue() + data.getYValue());
					Tooltip.install(data.getNode(), new Tooltip(data.getXValue() + data.getYValue()));
				}
			});
		}

	}

}