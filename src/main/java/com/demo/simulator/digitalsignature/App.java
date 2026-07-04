package com.demo.simulator.digitalsignature;

import java.security.SignatureException;

import com.demo.simulator.digitalsignature.crypto.KeyGeneratorService;
import com.demo.simulator.digitalsignature.crypto.SigningService;
import com.demo.simulator.digitalsignature.crypto.VerificationService;
import com.demo.simulator.digitalsignature.crypto.exception.SigningOperationException;

public class App {

	public static void main(String[] args) throws SigningOperationException {

		if (args.length == 0) {
			printUsage();
			return;
		}

		try {

			String command = args[0].toLowerCase();

			switch (command) {

			case "generate":

				new KeyGeneratorService().generateKeyPair("keys/private.key", "keys/public.key");

				System.out.println("Key pair generated.");

				break;

			case "sign":

				if (args.length != 4) {
					printUsage();
					return;
				}

				new SigningService().signFile(args[1], args[2], args[3]);

				System.out.println("File signed successfully.");

				break;

			case "verify":

				if (args.length != 4) {
					printUsage();
					return;
				}

				boolean verified = new VerificationService().verify(args[1], args[2], args[3]);

				System.out.println(verified ? "SIGNATURE VALID" : "SIGNATURE INVALID");

				break;

			default:
				printUsage();
			}

		} catch (SignatureException e) {

			System.err.println("Operation failed: " + e.getMessage());

			e.printStackTrace();
		}
	}

	private static void printUsage() {

		System.out.println();
		System.out.println("Usage:");
		System.out.println(" generate");
		System.out.println(" sign <file> <privateKey> <signature>");
		System.out.println(" verify <file> <signature> <publicKey>");
		System.out.println();
	}
}