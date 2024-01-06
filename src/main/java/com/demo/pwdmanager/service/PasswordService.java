package com.demo.pwdmanager.service;

import com.demo.pwdmanager.dao.PasswordDAO;

public class PasswordService {

	private PasswordDAO passwordDAO;

	public PasswordService() {
		this.passwordDAO = new PasswordDAO();
	}
}
