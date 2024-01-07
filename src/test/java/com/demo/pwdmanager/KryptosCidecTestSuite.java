package com.demo.pwdmanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.demo.pwdmanager.db.utils.CryptoUtil;
import com.demo.pwdmanager.db.utils.KeyStoreHandlerIntf;
import com.demo.pwdmanager.db.utils.KeyStoreUtilHandler;
import com.demo.pwdmanager.exceptions.PasswordManagerException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KryptosCidecTestSuite {

	private static final String KS_TEST_USERID = "soumya";
	private static final String KS_TEST_USERPWD = "So@13608";
	private static KeyStoreHandlerIntf keyStoreHandler;
	private KeyStore loadedKeyStore;

	@BeforeClass
	public static void init() {
		keyStoreHandler = new KeyStoreUtilHandler();
	}

	@Test
	public void test1_checkKeyStoreFolderExistence() {
		try {
			String keyStoreConfigPath = keyStoreHandler.getKeyStoreConfigPath();
			File file = new File(keyStoreConfigPath);
			assertTrue("The path is not a folder", file.isDirectory());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void test2_checkKeyStore_KeyFileExistence() {
		try {
			String keyStoreConfigPath = keyStoreHandler.getKeyStoreConfigPath();
			File file = new File(keyStoreConfigPath);
			assertTrue("The path is not a folder", file.isDirectory());
			assertNotNull("There are no files inside the config folder", file.listFiles()[0]);
			assertTrue("Files are missing", file.listFiles()[0].isFile());
			String fileName = file.listFiles()[0].getName();
			assertTrue("This is not a keyStore file", fileName.contains("keystore"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void test3_checkKeyStoreCreation() {
		try {
			boolean isCreated = keyStoreHandler.createKeyStore(KS_TEST_USERID, KS_TEST_USERPWD);
			assertTrue("keyStore was not created", isCreated);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void test4_checkKeyStoreLoad() {
		try {
			keyStoreHandler.loadKeyStore(KS_TEST_USERID, KS_TEST_USERPWD);
			loadedKeyStore = keyStoreHandler.getKs();
			assertNotNull("KeyStore is null", loadedKeyStore);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void test5_checkKeyStore_CredInsertion() {
		try {
			if ((loadedKeyStore = keyStoreHandler.getKs()) == null) {
				keyStoreHandler.loadKeyStore(KS_TEST_USERID, KS_TEST_USERPWD);
				loadedKeyStore = keyStoreHandler.getKs();
			} // load the keystore first
			keyStoreHandler.storeCredsInKS(KS_TEST_USERID, KS_TEST_USERPWD); // store the credentials into the keystore
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void test6_checkKeyStore_CredRetrieve() {
		try {
			if ((loadedKeyStore = keyStoreHandler.getKs()) == null) {
				keyStoreHandler.loadKeyStore(KS_TEST_USERID, KS_TEST_USERPWD);
				loadedKeyStore = keyStoreHandler.getKs();
			}
			assertNotNull("Keystore is null", loadedKeyStore);
			assertTrue("KeyStore does not contain data", loadedKeyStore.containsAlias("soumya-pwd-mngr"));
			SecretKey secretKey = keyStoreHandler.getKeyFromKS(KS_TEST_USERID, KS_TEST_USERPWD);
			System.out.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
			assertEquals("Not matched", "r0hZlu6BbSZLgO3SMDX8fs6HxKey9WAB8dTKNQHn5Kk=",
					Base64.getEncoder().encodeToString(secretKey.getEncoded()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test7_checkKeyStore_Decipher() throws PasswordManagerException, KeyStoreException, InvalidKeyException,
			NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException,
			IllegalBlockSizeException {
		if ((loadedKeyStore = keyStoreHandler.getKs()) == null) {
			keyStoreHandler.loadKeyStore(KS_TEST_USERID, KS_TEST_USERPWD);
			loadedKeyStore = keyStoreHandler.getKs();
		}
		assertNotNull("Keystore is null", loadedKeyStore);
		assertTrue("KeyStore does not contain data", loadedKeyStore.containsAlias("soumya-pwd-mngr"));
		SecretKey secretKey = keyStoreHandler.getKeyFromKS(KS_TEST_USERID, KS_TEST_USERPWD);
		String plainText1 = "hello";
		String plainText2 = "So@13608";
		String plainText3 = "This.IsFa$eBookP@ssW()rd";
		// get the IVParamSpec
		IvParameterSpec iv = CryptoUtil.generateIVFromPwd(KS_TEST_USERPWD);
		assertEquals("iv length not exact", 16, iv.getIV().length);
		String cipheredText = CryptoUtil.encrypt(plainText1, secretKey, iv);
		System.out.println("ciphered text: " + cipheredText);
		String decipheredText = CryptoUtil.decrypt(cipheredText, secretKey, iv);
		System.out.println("deciphered text:" + decipheredText);
		assertEquals(plainText1, decipheredText);

		cipheredText = CryptoUtil.encrypt(plainText2, secretKey, iv);
		System.out.println("ciphered text: " + cipheredText);
		decipheredText = CryptoUtil.decrypt(cipheredText, secretKey, iv);
		System.out.println("deciphered text:" + decipheredText);
		assertEquals(plainText2, decipheredText);

		cipheredText = CryptoUtil.encrypt(plainText3, secretKey, iv);
		System.out.println("ciphered text: " + cipheredText);
		decipheredText = CryptoUtil.decrypt(cipheredText, secretKey, iv);
		System.out.println("deciphered text:" + decipheredText);
		assertEquals(plainText3, decipheredText);
	}
}
