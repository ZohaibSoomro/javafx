package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable {

	
	@FXML private TableView<Student> table;
	@FXML private TableColumn<Student, Integer> id;
	@FXML private TableColumn<Student, String> name;
	@FXML private TableColumn<Student, String> surname;
	@FXML private TableColumn<Student, Integer> age;

	public ObservableList<Student> list = FXCollections.observableArrayList(
			new Student(42, "Zohaib Hassan", "Soomro", 18),
			new Student(43, "Amrat", "Merya", 19),
			new Student(44, "Syed Ahmad", "Shah",19),
			new Student(45, "Uzair", "Khuwaja", 21),
			new Student(46, "Arsam", "Chandio", 20));
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		id.setCellValueFactory(new PropertyValueFactory<Student,Integer>("Id"));
		name.setCellValueFactory(new PropertyValueFactory<Student,String>("Name"));
		surname.setCellValueFactory(new PropertyValueFactory<Student,String>("Surname"));
		age.setCellValueFactory(new PropertyValueFactory<Student,Integer>("Age"));

		table.setItems(list);
	}

}
