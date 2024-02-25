package com.demo.aopdemo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AGENT")
public class Agent implements Serializable {
	
	// TODO apply validations
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long agtId ;
	private String agtNum ;
	private String agtFname ;
	private String agtLname ;
	private BigDecimal paidComm ;

}
