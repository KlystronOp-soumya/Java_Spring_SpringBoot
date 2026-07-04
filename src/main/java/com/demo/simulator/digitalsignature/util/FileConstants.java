package com.demo.simulator.digitalsignature.util;

public final class FileConstants {

	private FileConstants() {
	}

	public static final int BUFFER_SIZE = 8192;

	public static final String RSA_ALGORITHM = "RSA";

	public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

	public static final int RSA_KEY_SIZE = 2048;

	public static final String DEFAULT_PRIVATE_KEY = "keys/private.key";

	public static final String DEFAULT_PUBLIC_KEY = "keys/public.key";
}
