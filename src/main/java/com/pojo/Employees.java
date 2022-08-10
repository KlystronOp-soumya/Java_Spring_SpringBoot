package com.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employees {
	private int emp_id;
	private String fname;
	private String lname;
	private String position;
	private int salary;
	
	
	
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "Employees [emp_id=" + emp_id + ", fname=" + fname + ", lname=" + lname + ", position=" + position
				+ ", salary=" + salary + "]";
	}
}
