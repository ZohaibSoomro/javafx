package attendanceSystem;

import java.sql.*;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public abstract class OracleRelatedTasks {
	private final static String username = "Scott";
	private final static String password = "Soomro";
	private static Connection conn = null;

	public static ResultSet executeAnyQuery(String query) {

		try {
			conn = oracleConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
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

	public static void createAttendanceTable() {
		try {
			conn = oracleConnection();
			String query = "Create table Attendance(id Number,status char(1),attendance_date timestamp default(localtimestamp))";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Table Attendance Created Successfully.", "Done",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public static boolean deletePreviousAttendance(JButton[] btnArray) {
		try {
			conn = oracleConnection();
			String query = "DELETE FROM Attendance Where id IN(" + btnArray[0].getText();
			for (int i = 1; i < btnArray.length; i++)
				query += "," + btnArray[i].getText();
			query += ")";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}

	public static int noOfOccurrence(String query) {
		try {
			ResultSet set = OracleRelatedTasks.executeAnyQuery(query);
			while (set.next()) {
				return set.getInt("COUNT(*)");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return 0;
	}
}
