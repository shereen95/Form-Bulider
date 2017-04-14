package com.java.backend;

import com.java.database.MysqlConnection;

public class User {
	private int userID = 0;
	private String firstName;
	private String lastName;
	private String userMail;
	private String userPassword;
	private MysqlConnection mysqlConnection = new MysqlConnection();

	public User(String firstName, String lastName, String userMail, String userPassword) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userMail = userMail;
		this.userPassword = userPassword;
		//userID = mysqlConnection.tableCount() + 1;
	}

	public int getUserID() {
		return userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserMail() {
		return userMail;
	}

	public String getUserPassword() {
		return userPassword;
	}

}