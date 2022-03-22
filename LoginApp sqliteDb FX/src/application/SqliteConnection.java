package application;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class SqliteConnection {
	
	
	public static Connection connector() {
		try {
			Class.forName("org.sqlite.JDBC");		
			Connection connection =DriverManager.getConnection("jdbc:sqlite:EmployeeDb.db");
			return connection;
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null,e);
			return null;
		}
		
		
	}
}
