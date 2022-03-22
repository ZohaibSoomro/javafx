package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StdData {

	SimpleIntegerProperty id, deptno, age;

	public int getId() {
		return id.get();
	}

	public int getDeptno() {
		return deptno.get();
	}

	public int getAge() {
		return age.get();
	}

	public String getName() {
		return name.get();
	}

	public String getSurname() {
		return surname.get();
	}

	SimpleStringProperty name, surname;

	public StdData(int id, String name, String surname, int deptno, int age) {
		this.id = new SimpleIntegerProperty(id);
		this.deptno = new SimpleIntegerProperty(deptno);
		this.age = new SimpleIntegerProperty(age);
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);
	}

}
