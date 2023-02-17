package io.github.lc.oss.commons.hashing;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;

import io.github.lc.oss.commons.encoding.Encoder;
import io.github.lc.oss.commons.encoding.Encodings;
import io.github.lc.oss.commons.util.Constants;

abstract class AbstractHasher implements Hasher {
    private static final int BUFFER_SIZE = 8 * Constants.FileSizes.MB;

    protected abstract String getHashName();

    @Override
    public String hash(byte[] data) {
        return this.hash(data, Encodings.Hex);
    }

    @Override
    public String hash(byte[] data, Encoder encoding) {
        return encoding.encode(this.getDigest().digest(data));
    }

    @Override
    public String hash(String data) {
        return this.hash(data, Encodings.Hex);
    }

    @Override
    public String hash(String data, Encoder encoding) {
        return encoding.encode(this.getDigest().digest(data.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String hash(Path file) {
        return this.hash(file, Encodings.Hex);
    }

    @Override
    public String hash(Path file, Encoder encoding) {
        MessageDigest digest = this.getDigest();
        try (InputStream is = new BufferedInputStream(Files.newInputStream(file))) {
            byte[] buffer = new byte[AbstractHasher.BUFFER_SIZE];
            int read;
            while ((read = is.read(buffer)) > 0) {
                digest.update(buffer, 0, read);
            }
            return encoding.encode(digest.digest());
        } catch (IOException ex) {
            throw new RuntimeException("Error hashing file", ex);
        }
    }

    protected MessageDigest getDigest() {
        try {
            return MessageDigest.getInstance(this.getHashName());
        } catch (Exception ex) {
            throw new RuntimeException("Unknown digest: " + this.getHashName(), ex);
        }
    }
}
