package com.demo.todoapp.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class ToDoEntity implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	//private Long id ;
	@NotNull
	private Long todoId ;
	@NotBlank
	private String todoDesc ;
	@NotBlank
	private String startDate ;
	@NotBlank
	private String endDate ;
	@NotBlank
	private char isCompleted ;
	
	
	
	

}
