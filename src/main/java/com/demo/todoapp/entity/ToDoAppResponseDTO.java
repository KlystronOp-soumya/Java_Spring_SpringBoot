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
	
	
	
	public ToDoAppResponseDTO(final Builder builder){
		this.isErrorPresent = builder.isErrorPresent ;
	}
	
		
	 static class Builder{
		
		 	private boolean isErrorPresent ;
			private ErrorMap errorMap ;
			private boolean isSucess ;
			private String path ;
			private String date ;
			private String time ;
			private String successMessage ;
			private int code ;
			
			public Builder setErrorPresent(final boolean errorPresent) {
				this.isErrorPresent = errorPresent ; //sets the value into the current object
				return this ; //returns the current object
			}
		
	}
	
}
