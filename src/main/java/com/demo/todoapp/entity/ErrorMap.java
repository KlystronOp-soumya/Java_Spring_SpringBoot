package com.demo.todoapp.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import lombok.Data;
import lombok.Setter;

@Data
public class ErrorMap implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private String errorExcep ;
	private String errorDesc ;
	private String errorCode ;
	private String errorDate ;
	private String errorTime ;
	private String path ;
	private Map<String , String> fieldErrorDetails ;
	

	
}
