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

class DigitalSignatureWorkFlowTest {

	@TempDir
	Path tempDir;

	private KeyGeneratorService keyGeneratorService;
	private SigningService digitalSigner;
	private VerificationService signatureVerifier;

	private Path privateKeyPath;
	private Path publicKeyPath;

	private Path inputFile;
	private Path signatureFile;

	@BeforeEach
	void setUp() throws Exception {

		keyGeneratorService = new KeyGeneratorService();
		digitalSigner = new SigningService();
		signatureVerifier = new VerificationService();

		privateKeyPath = tempDir.resolve("private.key");
		publicKeyPath = tempDir.resolve("public.key");

		inputFile = tempDir.resolve("contract.pdf");
		signatureFile = tempDir.resolve("contract.sig");

		Files.writeString(inputFile, "This is a digitally signed document.");

		keyGeneratorService.generateKeyPair(privateKeyPath.toString(), publicKeyPath.toString());
	}

	@Test
	@DisplayName("Complete Digital Signature Workflow")
	void shouldCompleteEntireDigitalSignatureWorkflow() throws Exception {

		// =====================================================
		// Step 1 : Sign the document
		// =====================================================

		digitalSigner.signFile(inputFile.toString(), privateKeyPath.toString(), signatureFile.toString());

		// =====================================================
		// Step 2 : Verify original document
		// =====================================================

		boolean originalVerification = signatureVerifier.verify(inputFile.toString(), signatureFile.toString(),
				publicKeyPath.toString());

		assertTrue(originalVerification, "Original document should verify successfully.");

		// =====================================================
		// Step 3 : Modify the document
		// =====================================================

		Files.writeString(inputFile, "\nThis line was maliciously appended.", StandardOpenOption.APPEND);

		// =====================================================
		// Step 4 : Verification must fail
		// =====================================================

		boolean modifiedVerification = signatureVerifier.verify(inputFile.toString(), signatureFile.toString(),
				publicKeyPath.toString());

		assertFalse(modifiedVerification, "Modified document should fail signature verification.");
	}
}