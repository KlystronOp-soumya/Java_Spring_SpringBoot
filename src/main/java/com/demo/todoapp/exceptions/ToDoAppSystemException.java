package com.demo.todoapp.exceptions;

import org.springframework.validation.Errors;

public class ToDoAppSystemException extends Exception {

	
	private static final long serialVersionUID = 1L;
	private String path , description ;
	private Errors errors ;
	public ToDoAppSystemException(final String message) {
		super(message) ;
	}
	
	public ToDoAppSystemException(final String message , final Throwable throwable) {
		super(message, throwable) ;
	}
	
	public ToDoAppSystemException(final String message , final String description , final String path) {
		super(message) ;
		this.description = description ;
		this.path = path ;
	}

	public ToDoAppSystemException(Errors errors, String description, String requestURI) {
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
