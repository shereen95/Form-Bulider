package com.java.backend;

public class Validation {

	public boolean ValidateName(String name) {
		return name.matches("([a-zA-Z])+[a-zA-Z0-9]+(\\s*|\\-|\\_)+[a-zA-Z0-9]*");
	}

	public boolean ValidateEmail(String email) {
		return email.matches("([a-zA-Z0-9]|(\\.|\\-|\\_|\\#|\\!|\\$|\\%|\\^|\\&|\\*))+\\@+([a-zA-Z0-9]*)+\\.+com");
	}

	public boolean ValidatePassword(String password) {
		if (password.length() >= 4) {
			System.out.println("Strong");
			return true;
		}
		return false;
	}

	public boolean ValidateConfirmPassword(String password, String confirmPassword) {
		if (password.equals(confirmPassword)) {
			return true;
		}
		return false;
	}

}
