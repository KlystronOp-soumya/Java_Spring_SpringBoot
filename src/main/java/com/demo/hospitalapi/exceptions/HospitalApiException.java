package com.demo.hospitalapi.exceptions;

public class HospitalApiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HospitalApiException() {
		// TODO Auto-generated constructor stub
	}

	public HospitalApiException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public HospitalApiException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public HospitalApiException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public HospitalApiException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
