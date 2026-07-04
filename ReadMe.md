# Digital Signature Simulator

A production-style Java CLI application that demonstrates the implementation of **Digital Signatures** using the Java Cryptography Architecture (JCA).

The project allows users to generate RSA key pairs, digitally sign arbitrary files (PDFs, images, text documents, binaries, etc.), and verify their authenticity and integrity using public key cryptography.

---

## Features

- Generate **RSA 2048-bit** key pairs.
- Sign any file using **SHA256withRSA**.
- Verify digital signatures.
- Buffered file processing (8 KB buffer) for memory efficiency.
- Industry-standard project structure.
- Configuration through external properties.
- Comprehensive JUnit 5 test suite.
- Clean separation of concerns following SOLID principles.

---

## Project Structure

```text
DigitalSignatureSimulator
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.demo.simulator.digitalsignature
│   │   │       ├── app
│   │   │       │   └── App.java
│   │   │       │
│   │   │       ├── config
│   │   │       │   └── PropertyLoader.java
│   │   │       │
│   │   │       ├── crypto
│   │   │       │   ├── KeyGeneratorService.java
│   │   │       │   ├── DigitalSigner.java
│   │   │       │   └── SignatureVerifier.java
│   │   │       │
│   │   │       ├── exception
│   │   │       │   └── DigitalSignatureException.java
│   │   │       │
│   │   │       └── util
│   │   │           └── FileUtils.java
│   │   │
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│       └── java
│           └── com.demo.simulator.digitalsignature
│               └── ...
│
├── keys
│   ├── private.key
│   └── public.key
│
├── signatures
│
└── README.md
```

---

# Technologies Used

- Java 17
- Java Cryptography Architecture (JCA)
- RSA (2048-bit)
- SHA-256
- JUnit 5
- Eclipse IDE

---

# Cryptographic Concepts Covered

This project demonstrates the following concepts:

- Public Key Cryptography
- RSA Key Pair Generation
- Digital Signatures
- SHA-256 Hashing
- Signature Verification
- PKCS#8 Private Key Encoding
- X.509 Public Key Encoding
- Secure File Processing
- Buffered Streams

---

# How Digital Signatures Work

Unlike encryption, digital signatures **do not encrypt the entire file**.

Instead, the process is:

```text
               Original File
                     │
                     ▼
             SHA-256 Hash Function
                     │
                     ▼
             256-bit Message Digest
                     │
                     ▼
      RSA Private Key Signature Operation
                     │
                     ▼
              Digital Signature (.sig)
```

Verification works as follows:

```text
Original File
      │
      ▼
SHA-256 Hash
      │
      │
      ├───────────────┐
      ▼               ▼
Computed Hash     Signature Verification
                      │
                      ▼
        Hash recovered using Public Key
                      │
                      ▼
             Compare Both Hashes
                      │
        ┌─────────────┴─────────────┐
        │                           │
        ▼                           ▼
     Valid                      Invalid
```

---

# Why Hash Instead of Encrypting the Whole File?

Encrypting an entire file with an RSA private key is impractical because:

- RSA is designed for small amounts of data.
- RSA encryption is computationally expensive.
- RSA has strict input size limitations.

Instead:

1. Compute the SHA-256 hash of the file.
2. Sign the hash using the private key.
3. Verify the hash using the public key.

This provides:

- Authenticity
- Integrity
- Non-Repudiation

without the overhead of encrypting the complete file.

---

# Building the Project

Compile all Java sources:

```bash
javac -d bin src/main/java/**/*.java
```

Or compile using Eclipse.

---

# Running the Application

## Generate RSA Key Pair

```bash
generate
```

Output:

```
private.key
public.key
```

---

## Sign a File

```bash
sign <file-path>
```

Example:

```bash
sign sample.pdf
```

Output:

```
sample.pdf.sig
```

---

## Verify Signature

```bash
verify <file-path> <signature-file>
```

Example:

```bash
verify sample.pdf sample.pdf.sig
```

Result:

```
Signature Verified Successfully
```

or

```
Signature Verification Failed
```

---

# Key Formats

| File | Format |
|-------|--------|
| private.key | PKCS#8 |
| public.key | X.509 |
| Signature | Binary (.sig) |

---

# Memory Efficiency

Large files are processed using buffered streams.

```java
byte[] buffer = new byte[8192];
```

Advantages:

- Constant memory usage
- Supports very large files
- Avoids OutOfMemoryError

---

# Testing

The project includes JUnit 5 tests covering:

- RSA key generation
- Key loading
- File signing
- Signature verification
- Tampered file detection
- Wrong public key detection
- Corrupted signature detection
- Empty file signing
- Binary file signing
- End-to-end workflow

---

# Learning Objectives

This project was developed to gain a practical understanding of:

- Java Cryptography Architecture (JCA)
- Digital Signatures
- RSA
- SHA-256
- Public Key Infrastructure fundamentals
- Secure file processing
- Unit testing cryptographic applications

It serves as a stepping stone toward more advanced topics such as:

- X.509 Certificates
- Java KeyStore
- PKCS#12
- TLS/SSL
- JWT
- OAuth2
- OpenID Connect
- Keycloak
- Enterprise Java Security

---

# Future Enhancements

- Support for EC (Elliptic Curve) Digital Signatures
- Java KeyStore integration
- PKCS#12 support
- Certificate-based signing
- Timestamp Authority (TSA)
- Detached and embedded signatures
- REST API using Quarkus
- Web interface
- Docker support
- CI/CD pipeline
- Benchmarking and performance analysis

---

# References

- Java Cryptography Architecture (JCA)
- Oracle Java Security Documentation
- PKCS#8 Specification
- X.509 Specification
- FIPS 180-4 (SHA-256)
- RFC 8017 (PKCS #1: RSA Cryptography Standard)

---

# License

This project is intended for educational purposes and demonstrates the implementation of digital signatures using standard Java cryptography libraries.

---

## Author

**Soumya Deep**

Java | Security | Backend Development | Enterprise Applications
