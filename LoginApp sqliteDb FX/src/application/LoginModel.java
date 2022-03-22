package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
	private Connection connection;

	public LoginModel() {
		connection = SqliteConnection.connector();

		if (connection == null)
			System.exit(1);
	}

	public boolean isDbConnected() {
		try {
			return !connection.isClosed();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	public boolean isLogin(String username1, String password1) throws SQLException {
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		String queryString = "select * from employee where username=? and password=?";

		try {
			pStatement = connection.prepareStatement(queryString);
			pStatement.setString(1, username1);
			pStatement.setString(2, password1);
			rSet = pStatement.executeQuery();

			if (rSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			rSet.close();
			pStatement.close();

		}

	}
}
