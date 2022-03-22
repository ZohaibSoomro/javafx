package application;

import java.net.*;
import java.util.*;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;

public class StdInfoController implements Initializable {
	@FXML
	TableView<StdData> table;
	@FXML
	TableColumn<StdData, Integer> id;
	@FXML
	TableColumn<StdData, String> name;
	@FXML
	TableColumn<StdData, String> surname;
	@FXML
	TableColumn<StdData, Integer> deptno;
	@FXML
	TableColumn<StdData, Integer> age;
	@FXML
	Label noOfRecords;
	public ObservableList<StdData> list = FXCollections.observableArrayList(new StdData(1, "Hi", "Soomro", 10, 18),
			new StdData(2, "Hi", "Soomro", 10, 18), new StdData(3, "Hi", "Soomro", 10, 18));

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		id.setCellValueFactory(new PropertyValueFactory<StdData, Integer>("ID"));
		name.setCellValueFactory(new PropertyValueFactory<StdData, String>("NAME"));
		surname.setCellValueFactory(new PropertyValueFactory<StdData, String>("SURNAME"));
		deptno.setCellValueFactory(new PropertyValueFactory<StdData, Integer>("DEPARTMENT"));
		age.setCellValueFactory(new PropertyValueFactory<StdData, Integer>("AGE"));
		noOfRecords.setText("2");
		table.setItems(list);
	}
}
