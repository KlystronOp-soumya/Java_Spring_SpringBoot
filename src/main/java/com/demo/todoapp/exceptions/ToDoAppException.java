package com.demo.todoapp.exceptions;

import javax.management.loading.PrivateClassLoader;

import org.springframework.validation.Errors;

public class ToDoAppException extends Exception {

	
	private static final long serialVersionUID = 1L;
	private String path , description ;
	private Errors errors ;
	public ToDoAppException(final String message) {
		super(message) ;
	}
	
	public ToDoAppException(final String message , final Throwable throwable) {
		super(message, throwable) ;
	}
	
	public ToDoAppException(final String message , final String description , final String path) {
		super(message) ;
		this.description = description ;
		this.path = path ;
	}

	public ToDoAppException(Errors errors, String description, String requestURI) {
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
