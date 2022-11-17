package com.github.lc.oss.commons.hashing.passwords;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PBKDF2 extends AbstractPasswordHasher {
    private static final int SALT_LENGTH = 16;

    private final String algorithm;
    private final int hashLenght;
    private final int iterations;

    public PBKDF2(String algorithm, int iterations, int hashLength) {
        this.algorithm = algorithm;
        this.iterations = iterations;
        this.hashLenght = hashLength;
    }

    protected String getAlgorithm() {
        return this.algorithm;
    }

    protected int getHashLenght() {
        return this.hashLenght;
    }

    protected int getIterations() {
        return this.iterations;
    }

    @Override
    protected int getSaltLength() {
        return PBKDF2.SALT_LENGTH;
    }

    @Override
    protected byte[] hash(char[] password, byte[] salt) {
        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance(this.getAlgorithm());
            PBEKeySpec s = new PBEKeySpec(password, salt, this.getIterations(), this.getHashLenght());
            return f.generateSecret(s).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException("Error hashing password", ex);
        }
    }
}
