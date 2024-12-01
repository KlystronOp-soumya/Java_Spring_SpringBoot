package com.demo.todoapp.exceptions;

public class ToDosNotFoundException extends Exception{

	
	private static final long serialVersionUID = 1L;
	
	private String path , description ;
	
	public ToDosNotFoundException(final String message , final String path , final String description) {
		super(message) ;
		this.path = path ;
		this.description = description ;
		
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
	
	

}
