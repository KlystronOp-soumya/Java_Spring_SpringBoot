package com.demo.pwdmanager.db.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.crypto.SecretKey;

import com.demo.pwdmanager.AppConstants;
import com.demo.pwdmanager.exceptions.PasswordManagerException;

/**
 * @author Soumyadeep Paul
 *
 */
public class KeyStoreUtilHandler implements KeyStoreHandlerIntf {

	private KeyStore ks;

	@Override
	public boolean createKeyStore(String userId, String keyStorePassword) throws PasswordManagerException, IOException {

		String keyStoreConfigPath = getKeyStoreConfigPath();// localappdata path
		String keyStoreFullPath = keyStoreConfigPath.concat("\\" + userId).concat(AppConstants.KEY_STORE.getValue());// generate
																														// the
																														// .keystore
		File f = new File(keyStoreFullPath); // file
		if (!f.exists()) {
			System.out.println("Keystore was created");
			f.createNewFile();
		}
		char[] pwdArray = keyStorePassword.toCharArray();
		try (BufferedOutputStream ksOutStream = new BufferedOutputStream(new FileOutputStream(f))) {
			this.ks = KeyStore.getInstance("pkcs12");
			this.ks.load(null, pwdArray);
			this.ks.store(ksOutStream, pwdArray); // this creates

		} catch (KeyStoreException e) {

			throw new PasswordManagerException("Issue with the keystore->\n" + e.getMessage(), e.getCause());
		} catch (FileNotFoundException fnof) {

			throw new PasswordManagerException("Issue with the key store file->\n" + fnof.getMessage(),
					fnof.getCause());
		} catch (IOException io) {

			throw new PasswordManagerException("Issue with the keystore->\n" + io.getMessage(), io.getCause());
		} catch (NoSuchAlgorithmException e) {
			throw new PasswordManagerException("Issue with the Keystore algortihm");

		} catch (CertificateException e) {
			throw new PasswordManagerException("Issue with the certificate in the keystore");
		}
		return true; // if not created then send false

	}

	@Override
	public void loadKeyStore(String userId, String password) throws PasswordManagerException {
		try (BufferedInputStream ksInputStream = new BufferedInputStream(
				new FileInputStream(getKeyStorePath(userId)))) {
			char[] pwdArray = password.toCharArray();
			this.ks = KeyStore.getInstance("pkcs12");
			ks.load(ksInputStream, pwdArray);
		} catch (Exception e) {
			throw new PasswordManagerException("Issue while loading the keystore->\n" + e.getMessage(), e.getCause());
		}

	}

	@Override
	public void loadKeyStore(String userId, String password, String keyStoreName, String path)
			throws PasswordManagerException {
		try (BufferedInputStream ksInputStream = new BufferedInputStream(
				new FileInputStream(getKeyStorePath(userId)))) {
			char[] pwdArray = password.toCharArray();
			this.ks = KeyStore.getInstance("pkcs12");
			ks.load(ksInputStream, pwdArray);
		} catch (Exception e) {
			throw new PasswordManagerException("Issue while loading the keystore->\n" + e.getMessage(), e.getCause());
		}
	}

	@Override
	public void storeCredsInKS(String userId, String keyStorePwd) throws PasswordManagerException {
		try (BufferedOutputStream ksOutputStream = new BufferedOutputStream(
				new FileOutputStream(getKeyStorePath(userId)))) {
			char[] keyStorePwdArray = keyStorePwd.toCharArray();
			SecretKey secretKey = CryptoUtil.getKeyFromPassword(keyStorePwd);
			KeyStore.SecretKeyEntry secretEntry = new KeyStore.SecretKeyEntry(secretKey);
			KeyStore.ProtectionParameter pwdProtParam = new KeyStore.PasswordProtection(keyStorePwdArray);
			this.ks.setEntry(userId.concat("-pwd-mngr"), secretEntry, pwdProtParam);
			this.ks.store(ksOutputStream, keyStorePwdArray);
		} catch (Exception e) {
			throw new PasswordManagerException("Could not insert secret into->\n" + e.getMessage(), e.getCause());
		}

	}

	@Override
	public SecretKey getKeyFromKS(final String userId, final String password) throws PasswordManagerException {
		SecretKey pwdMngrUsrSecret = null;
		char[] keyStorePwdArray = password.toCharArray();
		KeyStore.ProtectionParameter pwdProtParam = new KeyStore.PasswordProtection(keyStorePwdArray);
		try {
			Entry entry = ks.getEntry(userId.concat("-pwd-mngr"), pwdProtParam);

			// Cast the entry object to a KeyStore.SecretKeyEntry type
			KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) entry;

			// Get the SecretKey object from the entry
			pwdMngrUsrSecret = secretKeyEntry.getSecretKey();
			if (null == pwdMngrUsrSecret) {
				throw new NullPointerException("User id not found: " + userId);
			}

		} catch (Exception e) {
			throw new PasswordManagerException(e.getCause().toString());
		}
		return pwdMngrUsrSecret;
	}

	/**
	 * @param userId      The user id of the current user
	 * 
	 * @param secret      key The key generated using the password and the salt
	 *                    --One Time
	 * 
	 * @param keyStorePwd The user password is used as the keystore pwd
	 * 
	 */
	@Override
	public void storeCredsInKS(String userId, String secretKey, String keyStorePwd, KeyStore ks) {
		// No implementation
	}

	@Override
	public void deleteKeyStore(String userId) throws PasswordManagerException {
		try {
			Files.delete(Paths.get(getKeyStorePath(userId)));
		} catch (IOException e) {
			throw new PasswordManagerException("Could not delete the keystore->\n" + e.getMessage());
		}

	}

	@Override
	public String getKeyStoreConfigPath() {
		String localAppDataPath = System.getenv(AppConstants.APP_DATA.getValue());
		String keyStoreFullPath = localAppDataPath.concat("\\").concat(AppConstants.CONFIG_FOLDER.getValue());

		return keyStoreFullPath;
	}

	@Override
	public KeyStore getKs() {
		return this.ks;
	}

	private String getKeyStorePath(final String userId) {
		return getKeyStoreConfigPath().concat("\\" + userId).concat(AppConstants.KEY_STORE.getValue());
	}

}
