/**
 * 
 */
package com.demo.pwdmanager.db.utils;

import java.io.IOException;
import java.security.KeyStore;

import javax.crypto.SecretKey;

import com.demo.pwdmanager.exceptions.PasswordManagerException;

/**
 * @author USER
 *
 */
public interface KeyStoreHandlerIntf {

	public boolean createKeyStore(String userId, String keyStorePassword) throws PasswordManagerException, IOException; // keystore
	// name
	// should be
	// the current user name
	// fetch from
	// the
	// environment

	public void loadKeyStore(String userId, String password, String keyStoreName, String path)
			throws PasswordManagerException;

	public void loadKeyStore(String userId, String password) throws PasswordManagerException;

	public SecretKey getKeyFromKS(String userId, String password) throws PasswordManagerException;

	public void storeCredsInKS(String userId, String secretKey, String keyStorePwd, KeyStore ks)
			throws PasswordManagerException;

	public void storeCredsInKS(String userId, String keyStorePwd) throws PasswordManagerException;

	public void deleteKeyStore(String userId) throws PasswordManagerException;

	public String getKeyStoreConfigPath();

	default KeyStore getKs() {
		return null;
	}

}
