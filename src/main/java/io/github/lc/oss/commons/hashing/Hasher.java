package io.github.lc.oss.commons.hashing;

import java.nio.file.Path;

import io.github.lc.oss.commons.encoding.Encoder;

public interface Hasher {
    String hash(byte[] data);

    String hash(byte[] data, Encoder encoding);

    String hash(String data);

    String hash(String data, Encoder encoding);

    String hash(Path file);

    String hash(Path file, Encoder encoding);
}
