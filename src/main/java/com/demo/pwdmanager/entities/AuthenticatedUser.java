package com.demo.pwdmanager.entities;

import java.io.Serializable;

public class AuthenticatedUser implements Serializable {
	
	private String userId ;
	private boolean isVerified ;
	private boolean isLoggedIn ;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public boolean isVerified() {
		return isVerified;
	}
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	

}
