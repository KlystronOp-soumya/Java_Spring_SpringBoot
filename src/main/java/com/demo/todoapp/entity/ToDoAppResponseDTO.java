package com.demo.todoapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ToDoAppResponseDTO {
	
	private boolean isErrorPresent ;
	private ErrorMap errorMap ;
	private boolean isSucess ;
	private String path ;
	private String date ;
	private String time ;
	private String successMessage ;
	private int code ;
	
}
