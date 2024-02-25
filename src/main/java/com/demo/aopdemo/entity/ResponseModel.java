package com.demo.aopdemo.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResponseModel implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private int statusCode ;
	private boolean sucess ;
	private Object object ;
	private String errorMessage ;

}
