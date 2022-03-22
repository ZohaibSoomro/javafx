package attendanceSystem;
import java.sql.*;
import javax.swing.JOptionPane;

public abstract class OracleConnection {
	private final static String username = "Scott";
	private final static String password = "Soomro";
	private static Connection conn = null;

	public static ResultSet oracleResultSet(String query) {

		try {
			conn = oracleConnection();
			PreparedStatement stmt = conn.prepareStatement(query + " order by id");
			ResultSet set = stmt.executeQuery();
			return set;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

	public static Connection oracleConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", username, password);
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

	public static void createTables() {
		try {
			conn = oracleConnection();
			String query = "Create table teacher(id Number,name varchar(30),surname varchar(30),age number,"
					+ "email varchar(30),city varchar(30),hire_date date,constraint tid primary key(id))";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "Create table student(id Number,name varchar(30),surname varchar(30),age number,"
					+ "email varchar(30),city varchar(30),enrolled_on date,constraint sid primary key(id))";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			insertData();
			JOptionPane.showMessageDialog(null, "Table TEACHER,STUDENT created Successfully.", "Done",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private static void insertData() throws SQLException {
		conn = oracleConnection();
		String query = "insert into Teacher values(1,'Mohsin Ali','Memon',36,'mohsin78@gmail.com','Hyderabad','07-Mar-21')";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.executeUpdate();
		query = "insert into Teacher values(2,'Hira Noman','Memon',19,'hiraQueen@gmail.com','Qasimabad','28-Jun-18')";
		stmt = conn.prepareStatement(query);
		stmt.executeUpdate();
		query = "insert into Teacher values(3,'Shafia Qadeer','Memon',20,'shafia69@gmail.com','Jamshoro','04-Jan-19')";
		stmt = conn.prepareStatement(query);
		stmt.executeUpdate();

		query = "insert into student values(1,'Zohaib Hassan','Soomro',18,'zhs123@gmail.com','Mirpur Mathelo','27-Sep-19')";
		stmt = conn.prepareStatement(query);
		stmt.executeUpdate();
		query = "insert into student values(2,'Muhammad Uzair','Khuwaja',21,'khuwaja45@gmail.com','Badin','28-Sep-19')";
		stmt = conn.prepareStatement(query);
		stmt.executeUpdate();
		query = "insert into student values(3,'Syed Ahmad','Shah',20,'syed44@gmail.com','Talhar','28-Sep-19')";
		stmt = conn.prepareStatement(query);
		stmt.executeUpdate();
		query = "insert into student values(4,'Ali Noor','Khuwaja',19,'ali786@gmail.com','Hyderabad','30-Sep-19')";
		stmt = conn.prepareStatement(query);
		stmt.executeUpdate();

	}

}
