package com.demo.simulator.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.demo.simulator.digitalsignature.crypto.KeyGeneratorService;
import com.demo.simulator.digitalsignature.crypto.SigningService;
import com.demo.simulator.digitalsignature.crypto.VerificationService;

class SignatureVerifierTest {

	@TempDir
	private Path tempDir;

	private KeyGeneratorService keyGeneratorService;
	private SigningService signingService;
	private VerificationService verificationService;

	private Path privateKeyPath;
	private Path publicKeyPath;

	private Path sampleFile;
	private Path signatureFile;

	@BeforeEach
	void setUp() throws Exception {

		keyGeneratorService = new KeyGeneratorService();
		signingService = new SigningService();
		verificationService = new VerificationService();

		privateKeyPath = tempDir.resolve("private.key");
		publicKeyPath = tempDir.resolve("public.key");

		sampleFile = tempDir.resolve("sample.txt");
		signatureFile = tempDir.resolve("sample.sig");

		Files.writeString(sampleFile, "Hello Digital Signature");

		keyGeneratorService.generateKeyPair(privateKeyPath.toString(), publicKeyPath.toString());
	}

	@Test
	@DisplayName("Should verify valid signature")
	void shouldVerifySignatureSuccessfully() throws Exception {

		signingService.signFile(sampleFile.toString(), privateKeyPath.toString(), signatureFile.toString());

		boolean verified = verificationService.verify(sampleFile.toString(), signatureFile.toString(),
				publicKeyPath.toString());

		assertTrue(verified);

	}

	@Test
	@DisplayName("Should fail verification after file modification")
	void shouldFailVerificationForModifiedFile() throws Exception {

		signingService.signFile(sampleFile.toString(), privateKeyPath.toString(), signatureFile.toString());

		Files.writeString(sampleFile, "Modified", StandardOpenOption.APPEND);

		boolean verified = verificationService.verify(sampleFile.toString(), signatureFile.toString(),
				publicKeyPath.toString());

		assertFalse(verified);

	}

	@Test
	@DisplayName("Should fail verification using different public key")
	void shouldFailVerificationUsingWrongPublicKey() throws Exception {

		Path otherPrivate = tempDir.resolve("other-private.key");

		Path otherPublic = tempDir.resolve("other-public.key");

		keyGeneratorService.generateKeyPair(otherPrivate.toString(), otherPublic.toString());

		signingService.signFile(sampleFile.toString(), privateKeyPath.toString(), signatureFile.toString());

		boolean verified = verificationService.verify(sampleFile.toString(), signatureFile.toString(),
				otherPublic.toString());

		assertFalse(verified);

	}

	@Test
	@DisplayName("Should fail verification for corrupted signature")
	void shouldFailVerificationForCorruptedSignature() throws Exception {

		signingService.signFile(sampleFile.toString(), privateKeyPath.toString(), signatureFile.toString());

		byte[] signature = Files.readAllBytes(signatureFile);

		signature[0] ^= 0x01;

		Files.write(signatureFile, signature);

		boolean verified = verificationService.verify(sampleFile.toString(), signatureFile.toString(),
				publicKeyPath.toString());

		assertFalse(verified);

	}
}
