package com.demo.simulator.digitalsignature.crypto.exception;

public class SigningOperationException extends Exception {

	public SigningOperationException(String message) {
		super(message);
	}

	public SigningOperationException(String message, Throwable cause) {
		super(message, cause);
	}
}
