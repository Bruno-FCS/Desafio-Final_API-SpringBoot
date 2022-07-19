package com.brunofernandes.apispringboot.entities;

import java.io.Serializable;

public class PasswordChange implements Serializable {
	private static final long serialVersionUID = 1L;

	private String user_password;
	private String user_former_password;

	public PasswordChange() {
	}

	public PasswordChange(String user_password, String user_former_password) {
		super();
		this.user_password = user_password;
		this.user_former_password = user_former_password;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_former_password() {
		return user_former_password;
	}

	public void setUser_former_password(String user_former_password) {
		this.user_former_password = user_former_password;
	}
}
