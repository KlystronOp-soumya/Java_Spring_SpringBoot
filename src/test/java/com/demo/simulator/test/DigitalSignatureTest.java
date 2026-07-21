package com.demo.simulator.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.demo.simulator.digitalsignature.crypto.KeyGeneratorService;
import com.demo.simulator.digitalsignature.crypto.SigningService;

class DigitalSignatureTest {

	@TempDir
	private Path tempDir;

	private KeyGeneratorService keyGeneratorService;
	private SigningService signingService;

	private Path privateKeyPath;
	private Path publicKeyPath;

	private Path sampleFile;
	private Path signatureFile;

	@BeforeEach
	void setUp() throws Exception {

		keyGeneratorService = new KeyGeneratorService();
		signingService = new SigningService();

		privateKeyPath = tempDir.resolve("private.key");
		publicKeyPath = tempDir.resolve("public.key");

		sampleFile = tempDir.resolve("sample.txt");
		signatureFile = tempDir.resolve("sample.sig");

		Files.writeString(sampleFile, "Hello Digital Signature");

		keyGeneratorService.generateKeyPair(privateKeyPath.toString(), publicKeyPath.toString());
	}

	@Test
	@DisplayName("Should generate signature for a valid file")
	void shouldGenerateSignatureSuccessfully() throws Exception {

		// Act
		signingService.signFile(sampleFile.toString(), privateKeyPath.toString(), signatureFile.toString());

		// Assert
		assertAll(

				() -> assertTrue(Files.exists(signatureFile), "Signature file should exist"),

				() -> assertTrue(Files.size(signatureFile) > 0, "Signature file should not be empty")

		);

	}

	@Test
	@DisplayName("Should throw exception when file does not exist")
	void shouldThrowExceptionForMissingInputFile() {

		Path missingFile = tempDir.resolve("missing.pdf");

		assertThrows(Exception.class, () -> signingService.signFile(missingFile.toString(), privateKeyPath.toString(),
				signatureFile.toString()));

	}

	@Test
	@DisplayName("Should throw exception for missing private key")
	void shouldThrowExceptionForMissingPrivateKey() {

		Path missingPrivateKey = tempDir.resolve("missing.key");

		assertThrows(Exception.class, () -> signingService.signFile(sampleFile.toString(), missingPrivateKey.toString(),
				signatureFile.toString()));

	}

	@Test
	@DisplayName("Should sign an empty file")
	void shouldSignEmptyFile() throws Exception {

		Path emptyFile = tempDir.resolve("empty.txt");

		Files.createFile(emptyFile);

		signingService.signFile(emptyFile.toString(), privateKeyPath.toString(), signatureFile.toString());

		assertTrue(Files.exists(signatureFile));

	}

	@Test
	@DisplayName("Should sign binary file")
	void shouldSignBinaryFile() throws Exception {

		Path binaryFile = tempDir.resolve("sample.bin");

		byte[] randomBytes = new byte[4096];

		new java.security.SecureRandom().nextBytes(randomBytes);

		Files.write(binaryFile, randomBytes);

		signingService.signFile(binaryFile.toString(), privateKeyPath.toString(), signatureFile.toString());

		assertTrue(Files.exists(signatureFile));

	}

}
