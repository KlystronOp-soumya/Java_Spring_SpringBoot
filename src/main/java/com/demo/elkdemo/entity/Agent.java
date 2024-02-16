package com.demo.elkdemo.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Agent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long agtId ;
	private Long agtNum ;
	private String agtFName ;
	private String agtLName ;

}
