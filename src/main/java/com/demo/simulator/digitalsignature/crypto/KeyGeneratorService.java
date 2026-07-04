package com.demo.simulator.digitalsignature.crypto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import com.demo.simulator.digitalsignature.crypto.exception.SigningOperationException;
import com.demo.simulator.digitalsignature.util.FileConstants;

public class KeyGeneratorService {

	public void generateKeyPair(String privateKeyPath, String publicKeyPath) throws SigningOperationException {

		try {

			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(FileConstants.RSA_ALGORITHM);

			keyPairGenerator.initialize(FileConstants.RSA_KEY_SIZE);

			KeyPair keyPair = keyPairGenerator.generateKeyPair();

			Files.write(Path.of(privateKeyPath), keyPair.getPrivate().getEncoded());

			Files.write(Path.of(publicKeyPath), keyPair.getPublic().getEncoded());

		} catch (NoSuchAlgorithmException e) {

			throw new SigningOperationException("RSA algorithm unavailable", e);

		} catch (IOException e) {

			throw new SigningOperationException("Unable to write key files", e);
		}
	}

}
