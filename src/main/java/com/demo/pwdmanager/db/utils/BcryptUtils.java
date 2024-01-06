package com.demo.pwdmanager.db.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 * @author Soumyadeep
 * 
 * Class to encode and decode a string using Brcypt algo
 * 
 *   The Bcrypt is not available in Java out of the box but it is a standard in Spring
 * 
 * */
public class BcryptUtils {

	private transient BCryptPasswordEncoder encoder;

	public BcryptUtils() {

		this.encoder = new BCryptPasswordEncoder(13);

	}

	public String getEncodedPwd(final String rawPassword) {
		return this.encoder.encode(rawPassword);
	}

	public boolean decodePwd(final String rawPassword, final String encodedPwd) {
		return this.encoder.matches(rawPassword, encodedPwd);
	}

}
