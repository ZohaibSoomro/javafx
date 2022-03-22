package application;

import java.beans.EventHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MainController {

	@FXML
	private PieChart pieChart;
	
	@FXML
	private Label status;
	
	public void btn(ActionEvent event) {
		ObservableList<Data> list= FXCollections.observableArrayList(
				new PieChart.Data("Java",80),
				new PieChart.Data("Cpp",70),
				new PieChart.Data("Python",10),
				new PieChart.Data("C#",20),
				new PieChart.Data("C",60));
		
		pieChart.setData(list);
		for (final PieChart.Data data : pieChart.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new javafx.event.EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					status.setText(String.valueOf(data.getPieValue())+"%");
				}
			});
		}
		
	}
	
}
