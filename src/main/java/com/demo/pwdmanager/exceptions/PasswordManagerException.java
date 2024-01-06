package com.demo.pwdmanager.exceptions;

public class PasswordManagerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordManagerException(final String message, final Throwable cause) {

		super(message, cause);

	}

	public PasswordManagerException(final String message) {
		super(message);
	}

}
