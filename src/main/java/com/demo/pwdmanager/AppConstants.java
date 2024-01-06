package com.demo.pwdmanager;

public enum AppConstants {

	CONFIG_FOLDER("PasswordManager"), KEY_STORE(".keystore"), LOG_FOLDER("logs"), USER_ENV("USERNAME"),
	APP_DATA("LOCALAPPDATA"), CIPHER_ALGO("AES/CBC/PKCS5Padding");

	private String value;

	AppConstants(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
