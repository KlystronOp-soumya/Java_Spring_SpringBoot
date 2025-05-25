package com.demo.todoapp.exceptions;

import org.springframework.validation.Errors;

public class TodoCreationException extends RuntimeException{
	
	private String path , description ;
	private Errors errors ;
	public TodoCreationException(final String message) {
		super(message) ;
	}
	
	public TodoCreationException(final String message , final Throwable throwable) {
		super(message, throwable) ;
	}
	
	public TodoCreationException(final String message , final String description , final String path) {
		super(message) ;
		this.description = description ;
		this.path = path ;
	}

	public TodoCreationException(Errors errors, String description, String requestURI) {
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
