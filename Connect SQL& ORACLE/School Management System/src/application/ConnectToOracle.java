package application;

import java.sql.*;

import javax.swing.JOptionPane;

public class ConnectToOracle {
	public static Connection getORCLConnection() {
		Connection connection=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","Soomro");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		return connection;
	}
}
