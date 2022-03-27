package com.demo.jdbcDemo.entity;

public class ActorEntity {

	private int id;
	private String fname;
	private String lname;
	
	public ActorEntity() {
		super();
	}
	public ActorEntity(int id, String fname, String lname) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "ActorEntity [id=" + id + ", fname=" + fname + ", lname=" + lname + "]";
	}
	
	
}
