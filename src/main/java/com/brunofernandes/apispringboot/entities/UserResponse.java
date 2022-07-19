package com.brunofernandes.apispringboot.entities;

import java.io.Serializable;

public class UserResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long user_id;
	private String user_name;
	private String user_email;
	private String user_full_name;

	public UserResponse() {
	}

	public UserResponse(Long user_id, String user_name, String user_email, String user_full_name) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_full_name = user_full_name;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_full_name() {
		return user_full_name;
	}

	public void setUser_full_name(String user_full_name) {
		this.user_full_name = user_full_name;
	}
}
