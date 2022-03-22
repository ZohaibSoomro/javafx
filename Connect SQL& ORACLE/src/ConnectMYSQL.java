import java.sql.*;
import javax.swing.JOptionPane;

public class ConnectMYSQL {
	public static Connection ConnectSQL() throws Exception {
		Connection connection = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		JOptionPane.showMessageDialog(null, "loaded!");
		connection = DriverManager.getConnection("jdbc:mysql://@localhost:3306/student", "root", "");
		return connection;
	}

	public static void main(String[] args) throws Exception {
		Connection connection = ConnectSQL();
		PreparedStatement statement= connection.prepareStatement("Select * from student");
		ResultSet set= statement.executeQuery();
		while (set.next()) {
			for (int i = 1; i < 5; i++) {
				System.out.printf("%8s",set.getString(i)+" ");
			}
			System.out.println();
		}
	}

}
