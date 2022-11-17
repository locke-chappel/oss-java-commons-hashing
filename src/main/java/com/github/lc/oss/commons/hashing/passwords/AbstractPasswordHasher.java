package com.github.lc.oss.commons.hashing.passwords;

import java.security.SecureRandom;

import com.github.lc.oss.commons.encoding.Encoder;
import com.github.lc.oss.commons.encoding.Encodings;

public abstract class AbstractPasswordHasher implements PasswordHasher {
    private static final String DELIMITER = "$";

    protected abstract int getSaltLength();

    protected Encoder getEncoder() {
        return Encodings.Base64;
    }

    protected String getDelimiter() {
        return AbstractPasswordHasher.DELIMITER;
    }

    @Override
    public String hash(char[] password) {
        byte[] salt = this.randomBytes(this.getSaltLength());
        byte[] hash = this.hash(password, salt);
        return this.encode(salt, hash);
    }

    @Override
    public boolean matches(char[] password, CharSequence hash) {
        byte[] salt = this.getSalt(hash);
        byte[] challenge = this.hash(password, salt);
        String encoded = this.encode(salt, challenge);
        return encoded.equals(hash);
    }

    protected String encode(byte[] salt, byte[] hash) {
        Encoder e = this.getEncoder();
        return String.format("%s%s%s%s", this.getDelimiter(), e.encode(salt), this.getDelimiter(), e.encode(hash));
    }

    protected byte[] getSalt(CharSequence hash) {
        String h = hash.toString();
        return this.getEncoder().decode(h.substring(1, h.indexOf(this.getDelimiter(), 1)));
    }

    protected abstract byte[] hash(char[] password, byte[] salt);

    protected byte[] randomBytes(int length) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return bytes;
    }
}
