package com.brunofernandes.apispringboot.services.exceptions;

public class AuthenticationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AuthenticationException() {
		super("Invalid authentication");
	}
}
