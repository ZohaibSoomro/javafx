import java.sql.*;

import javax.swing.JOptionPane;

public class ConnectOracle {
	public static Connection connectORACLE() throws Exception {
		Connection connection = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		JOptionPane.showMessageDialog(null, "Loaded!");
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "Soomro");

		return connection;
	}

	public static void main(String[] args) throws Exception {
		Connection connection=connectORACLE();
		String query="Select * from emp" ;
		Statement stmtStatement= connection.createStatement();
		ResultSet rSet= stmtStatement.executeQuery(query);
		
		while (rSet.next()) {
			for (int i = 1; i <= 8; i++) {
				System.out.printf("%8S",rSet.getString(i)+" ");
			}
			System.out.println();
		}
	}

}
