package com.demo.simulator.digitalsignature.crypto;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.X509EncodedKeySpec;

import com.demo.simulator.digitalsignature.util.FileConstants;

public final class VerificationService {

	public boolean verify(String filePath, String signatureFilePath, String publicKeyPath) throws SignatureException {

		try {

			PublicKey publicKey = loadPublicKey(publicKeyPath);

			byte[] signatureBytes = Files.readAllBytes(Path.of(signatureFilePath));

			Signature signature = Signature.getInstance(FileConstants.SIGNATURE_ALGORITHM);

			signature.initVerify(publicKey);

			// HASHING recompute the SHA-256 hash from file

			try (FileInputStream fis = new FileInputStream(filePath)) {

				byte[] buffer = new byte[FileConstants.BUFFER_SIZE];

				int bytesRead;

				while ((bytesRead = fis.read(buffer)) != -1) {

					signature.update(buffer, 0, bytesRead);
				}
			}

			// verify compare the computed hash

			return signature.verify(signatureBytes);
		} catch (Exception ex) {

			throw new SignatureException("Unable to verify the signature", ex);
		}
	}

	private PublicKey loadPublicKey(String publicKeyPath) throws Exception {

		byte[] keyBytes = Files.readAllBytes(Path.of(publicKeyPath));

		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);

		KeyFactory keyFactory = KeyFactory.getInstance(FileConstants.RSA_ALGORITHM);

		return keyFactory.generatePublic(spec);
	}
}
