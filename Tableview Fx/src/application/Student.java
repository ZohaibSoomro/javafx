package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {
	private final SimpleIntegerProperty id;
	private final SimpleStringProperty name;
	private final SimpleStringProperty surName;
	private final SimpleIntegerProperty age;

	public Student(Integer id, String name, String surName, Integer age) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.surName = new SimpleStringProperty(surName);
		this.age = new SimpleIntegerProperty(age);
	}

	public Integer getId() {
		return id.get();
	}

	public String getName() {
		return name.get();
	}

	public String getSurName() {
		return surName.get();
	}

	public Integer getAge() {
		return age.get();
	}

}
