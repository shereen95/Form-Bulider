package com.java.database;

import java.sql.*;

import com.java.backend.User;

public class MysqlConnection {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/new_schema";
	static final String USER = "root";
	static final String PASS = "UdemySite_2018";
	private Statement stmt = null;
	private Connection conn = null;
	private static int userID;

	public void createTable(String sql, String tableName) {
		DatabaseMetaData dbm = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			// check if table is created
			ResultSet tables = null;
			try {
				dbm = conn.getMetaData();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			try {
				tables = dbm.getTables(null, null, tableName, null);

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				if (tables.next()) {
					System.out.println("Table already exists!");
				} else {
					stmt.executeUpdate(sql);
				}

			} catch (SQLException se) {
				// Handle errors for JDBC
				se.printStackTrace();
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	public void addNewRecord(String sqlStr) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

		} catch (ClassNotFoundException | SQLException e2) {
			e2.printStackTrace();
		}

		String sql = "CREATE TABLE Users " + "(id INTEGER not NULL AUTO_INCREMENT, " + " first_name VARCHAR(255), "
				+ " last_name VARCHAR(255), " + " email VARCHAR(255), " + " password VARCHAR(255), "
				+ " PRIMARY KEY ( id ))";
		createTable(sql, "Users");

		String sql2 = "CREATE TABLE Forms " + "(id INTEGER not NULL AUTO_INCREMENT, " + " name VARCHAR(255), "
				+ " PRIMARY KEY ( id ), " + "users_id INTEGER, " + "FOREIGN KEY (users_id) REFERENCES Users(id))";
		createTable(sql2, "Forms");

		try {
			stmt.executeUpdate(sqlStr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean login(String email, String password) {
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e2) {
			e2.printStackTrace();
		}

		String sql = "SELECT password from Users WHERE email = '" + email + "'";
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String str = null;
		try {
			if (rs.next())
				str = rs.getString("password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (str != null && str.equals(password)) {

			String sql2 = "SELECT id from Users WHERE email = '" + email + "'";
			try {
				rs = stmt.executeQuery(sql2);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (rs.next())
					userID = Integer.parseInt(rs.getString("id"));
				// System.out.println(userID+"alooo");

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return true;

		} else
			return false;
	}

	public int getUserID() {
		return userID;
	}
	/*
	 * public int tableCount() { try { Class.forName("com.mysql.jdbc.Driver");
	 * conn = DriverManager.getConnection(DB_URL, USER, PASS); stmt =
	 * conn.createStatement(); } catch (ClassNotFoundException | SQLException
	 * e2) { e2.printStackTrace(); }
	 * 
	 * String sql = "SELECT COUNT(*) AS id FROM Users"; ResultSet rs = null; try
	 * { rs = stmt.executeQuery(sql); } catch (SQLException e) {
	 * e.printStackTrace(); }
	 * 
	 * int count = 0; try { if (rs.next()) count =
	 * Integer.parseInt(rs.getString("id")); } catch (NumberFormatException |
	 * SQLException e) { e.printStackTrace(); } return count; }
	 */

}
