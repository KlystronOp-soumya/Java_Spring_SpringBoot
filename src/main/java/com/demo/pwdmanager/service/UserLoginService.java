package com.demo.pwdmanager.service;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

import com.demo.pwdmanager.dao.UserLoginDAOImpl;
import com.demo.pwdmanager.db.utils.BcryptUtils;
import com.demo.pwdmanager.entities.AuthenticatedUser;
import com.demo.pwdmanager.entities.UserEntity;
import com.demo.pwdmanager.exceptions.PasswordManagerException;

public class UserLoginService {

	private transient UserLoginDAOImpl loginDAO;
	private transient BcryptUtils bcryptUtils;
	private transient int LOGIN_ATTEMPT = 0;
	private transient String userId;
	private transient String password;

	public UserLoginService() {
		this.loginDAO = loginDAO;
		this.bcryptUtils = new BcryptUtils();
	}

	public UserLoginService(final UserLoginDAOImpl loginDAO) {
		this.loginDAO = loginDAO;
	}

	public AuthenticatedUser doLogin() {
		BufferedReader br = null;
		AuthenticatedUser authenticatedUser = null;
		boolean isVerified = false;
		com.demo.pwdmanager.entities.Principal principal = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please enter user id: ");
			this.userId = br.readLine();
			System.out.println("Please enter password: ");
			this.password = br.readLine();
			principal = new com.demo.pwdmanager.entities.Principal(userId, password);
			UserEntity userEntity = loginDAO.verifyLogin(principal);
			if (!this.bcryptUtils.decodePwd(password, userEntity.getEncodedPassword())) {
				this.LOGIN_ATTEMPT++; // increase the attempt counter for each try
			} else {
				authenticatedUser = new AuthenticatedUser();
				authenticatedUser.setUserId(userId);
				authenticatedUser.setLoggedIn(true);
				authenticatedUser.setVerified(isVerified);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return authenticatedUser;
	}

	public int getLogInAttempt() {
		return this.LOGIN_ATTEMPT;
	}

	public UserEntity registerNewUser() throws PasswordManagerException {
		// initialize
		BufferedReader br = null;
		Console console = System.console();
		UserEntity registeredUser = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter user id:: ");
			String userId = br.readLine();
			System.out.println("Enter password:: ");
			/*
			 * Take this user password The password will be stored in an encrypted manner in
			 * the database
			 * 
			 * The same password will be used to create the keystore and set the same
			 * password as the KS password
			 * 
			 * Take the user name from the current machine from the environment variable
			 */
			String userPassword = console.readPassword().toString();// to hide the password given through console
			registeredUser = new UserEntity();
			registeredUser.setUserId(userId);
			registeredUser.setEncodedPassword(this.bcryptUtils.getEncodedPwd(userPassword));
			// save using DAO
			this.loginDAO.registerUser(registeredUser);

		} catch (Exception e) {
			// Log the error
			throw new PasswordManagerException("Something went wrong!! check logs");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				throw new PasswordManagerException("Reader can not be closed");
			}
		}

		return registeredUser;

	}

}
