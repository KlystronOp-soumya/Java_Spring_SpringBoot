package com.demo.simulator.digitalsignature.crypto;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;

import com.demo.simulator.digitalsignature.util.FileConstants;

public class SigningService {

	public void signFile(final String filePath, final String privateKeyPath, final String signaturePath)
			throws SignatureException {

		try {

			PrivateKey privateKey = loadPrivateKey(privateKeyPath);

			Signature signature = Signature.getInstance(FileConstants.SIGNATURE_ALGORITHM);

			signature.initSign(privateKey);

			// Hashing phase; read file in chunks and create the hash

			try (BufferedInputStream fis = new BufferedInputStream(new FileInputStream(filePath))) {

				int bytesRead;

				byte[] buffer = new byte[FileConstants.BUFFER_SIZE];

				while ((bytesRead = fis.read(buffer)) != -1) {

					signature.update(buffer, 0, bytesRead);
				}
			}

			// signing phase; RSA to sign the computed digest

			byte[] digitalSignature = signature.sign();

			try (BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(signaturePath))) {

				fos.write(digitalSignature);
			}

		} catch (Exception ex) {

			throw new SignatureException("Unable to sign file: " + filePath, ex);
		}
	}

	private PrivateKey loadPrivateKey(String privateKeyPath) throws Exception {

		byte[] keyBytes = Files.readAllBytes(Path.of(privateKeyPath));

		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);

		KeyFactory keyFactory = KeyFactory.getInstance(FileConstants.RSA_ALGORITHM);

		return keyFactory.generatePrivate(spec);
	}
}
