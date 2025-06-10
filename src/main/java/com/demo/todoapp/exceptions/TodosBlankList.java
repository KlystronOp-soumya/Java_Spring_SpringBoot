package com.demo.todoapp.exceptions;

public class TodosBlankList extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1795921774998529947L;
	
	public TodosBlankList() {
	
	}
	
	public TodosBlankList(String message){
		super(message) ;
	}

}
