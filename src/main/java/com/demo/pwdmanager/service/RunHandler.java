package com.demo.pwdmanager.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import com.demo.pwdmanager.db.utils.KeyStoreUtilHandler;
import com.demo.pwdmanager.entities.AuthenticatedUser;
import com.demo.pwdmanager.exceptions.PasswordManagerException;

public class RunHandler {

	// TODO: Check for Schema and table existence is a single different thread
	// TODO: Simultaneously check for the keystore existence
	// TODO: create the folder
	private UserLoginService userLoginService;
	private PasswordService passwordService;

	public RunHandler() {
		this.userLoginService = new UserLoginService();
		this.passwordService = new PasswordService();
	}

	public void run() {

		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/mm/dd");
		SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm");
		TimeZone tzone = TimeZone.getDefault();
		Calendar calendar = Calendar.getInstance();
		System.out.println("Welcome to Your Password Manager");
		AuthenticatedUser alreadyAuthenticated;
		try {
			// 1. Check for the Config folder
			createConfigFolder();
			// try to establish database connection in a thread

			// if option 1: login
			do {
				// always go for a login attempt for once if failed
				// if the login attempt is more than 3 then delete the keystore and database
				// file

				alreadyAuthenticated = this.userLoginService.doLogin();
				if (alreadyAuthenticated.isVerified()) // if already verified then
				{
					break;
				}
				System.out.println(
						"Could not login::You have only " + (3 - this.userLoginService.getLogInAttempt()) + "s left");

			} while (this.userLoginService.getLogInAttempt() > 0 && this.userLoginService.getLogInAttempt() <= 3);
			/* WARNING */
			if (this.userLoginService.getLogInAttempt() == 3 && !alreadyAuthenticated.isVerified()) {
				// delete everything and exit from the system
			} else {
				// go for the further prompt
				// 1 to show password always go for the

			}

		} catch (Exception e) {

		}

	}

	// always run this method because config files could be deleted due false
	// attempts
	// the keystore will be created during next registration
	private void createConfigFolder() throws PasswordManagerException {
		KeyStoreUtilHandler keyStoreHandler = new KeyStoreUtilHandler();
		File configFolder = new File(keyStoreHandler.getKeyStoreConfigPath());
		if (!configFolder.exists()) {
			configFolder.mkdir();
			if (!configFolder.isDirectory())
				throw new PasswordManagerException("Config folder could not be created");
		} else {
			System.out.println("Configuration is ok");
		}
	}
}
