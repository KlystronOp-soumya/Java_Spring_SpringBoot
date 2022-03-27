package com.demo.poiDemo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmployeeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String empName;
	private String empDesig;
	private BigDecimal empSal;

	public EmployeeEntity() {
		// TODO Auto-generated constructor stub
	}

	public EmployeeEntity(String empName, String empDesig, BigDecimal empSal) {
		super();
		this.empName = empName;
		this.empDesig = empDesig;
		this.empSal = empSal;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDesig() {
		return empDesig;
	}

	public void setEmpDesig(String empDesig) {
		this.empDesig = empDesig;
	}

	public BigDecimal getEmpSal() {
		return empSal;
	}

	public void setEmpSal(BigDecimal empSal) {
		this.empSal = empSal;
	}

}
