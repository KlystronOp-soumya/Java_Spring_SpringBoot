package com.demo.todoapp.exceptions;

import org.springframework.validation.Errors;

public class TodoCanNotUpdateException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String path , description ;
	private Errors errors ;
	
	public TodoCanNotUpdateException() {
		super();
	}

	public TodoCanNotUpdateException(final String message) {
		super(message) ;
	}
	
	public TodoCanNotUpdateException(final String message , final Throwable throwable) {
		super(message, throwable) ;
	}
	
	public TodoCanNotUpdateException(final String message , final String description , final String path) {
		super(message) ;
		this.description = description ;
		this.path = path ;
	}

	public TodoCanNotUpdateException(Errors errors, String description, String requestURI) {
		this.errors = errors ;
		this.description = description ;
		this.path = requestURI ;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Errors getErrors() {
		return errors;
	}

}
