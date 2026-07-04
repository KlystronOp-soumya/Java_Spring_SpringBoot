package com.demo.simulator.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.demo.simulator.digitalsignature.crypto.KeyGeneratorService;

class KeyGeneratorServiceTest {

	@TempDir
	Path tempDir;

	private Path privateKeyPath;
	private Path publicKeyPath;
	private KeyGeneratorService keyGeneratorService;

	@BeforeEach
	void setUp() {
		keyGeneratorService = new KeyGeneratorService();
		privateKeyPath = tempDir.resolve("private.key");
		publicKeyPath = tempDir.resolve("public.key");
	}

	@Test
	@DisplayName("Generate RSA key pair successfully")
	void shouldGeneratePublicAndPrivateKeysSuccessfully() throws Exception {

		// Act
		keyGeneratorService.generateKeyPair(privateKeyPath.toString(), publicKeyPath.toString());

		// Assert
		assertAll(

				() -> assertTrue(Files.exists(privateKeyPath), "Private key file should exist"),

				() -> assertTrue(Files.exists(publicKeyPath), "Public key file should exist"),

				() -> assertTrue(Files.size(privateKeyPath) > 0, "Private key should not be empty"),

				() -> assertTrue(Files.size(publicKeyPath) > 0, "Public key should not be empty")

		);
	}

	@Test
	@DisplayName("Should generate valid RSA keys")
	void shouldGenerateValidRSAKeys() throws Exception {

		keyGeneratorService.generateKeyPair(privateKeyPath.toString(), publicKeyPath.toString());

		byte[] privateBytes = Files.readAllBytes(privateKeyPath);
		byte[] publicBytes = Files.readAllBytes(publicKeyPath);

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateBytes));

		PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicBytes));

		// Assert
		assertAll(

				() -> assertNotNull(privateKey),

				() -> assertNotNull(publicKey),

				() -> assertEquals("RSA", privateKey.getAlgorithm()),

				() -> assertEquals("RSA", publicKey.getAlgorithm()),

				() -> assertEquals("PKCS#8", privateKey.getFormat()),

				() -> assertEquals("X.509", publicKey.getFormat())

		);
	}
}
