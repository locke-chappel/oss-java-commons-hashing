package com.github.lc.oss.commons.hashing;

import java.nio.file.Path;
import java.util.Set;

import com.github.lc.oss.commons.encoding.Encoder;
import com.github.lc.oss.commons.util.TypedEnumCache;

public enum Hashes implements Hasher {
    MD5(new CommonHasher("MD5")),
    SHA1(new CommonHasher("SHA-1")),
    SHA2_256(new CommonHasher("SHA-256")),
    SHA2_384(new CommonHasher("SHA-384")),
    SHA2_512(new CommonHasher("SHA-512")),

    SHA3_256(new CommonHasher("SHA3-256")),
    SHA3_384(new CommonHasher("SHA3-384")),
    SHA3_512(new CommonHasher("SHA3-512"));

    private static final TypedEnumCache<Hashes, Hasher> CACHE = new TypedEnumCache<>(Hashes.class, false);

    public static final Set<Hasher> all() {
        return Hashes.CACHE.values();
    }

    public static Hasher byName(String name) {
        return Hashes.CACHE.byName(name);
    }

    public static boolean hasName(String name) {
        return Hashes.CACHE.hasName(name);
    }

    public static Hasher tryParse(String name) {
        return Hashes.CACHE.tryParse(name);
    }

    private final Hasher hasher;

    private Hashes(Hasher hasher) {
        this.hasher = hasher;
    }

    @Override
    public String hash(byte[] data) {
        return this.hasher.hash(data);
    }

    @Override
    public String hash(byte[] data, Encoder encoding) {
        return this.hasher.hash(data, encoding);
    }

    @Override
    public String hash(String data) {
        return this.hasher.hash(data);
    }

    @Override
    public String hash(String data, Encoder encoding) {
        return this.hasher.hash(data, encoding);
    }

    @Override
    public String hash(Path file) {
        return this.hasher.hash(file);
    }

    @Override
    public String hash(Path file, Encoder encoding) {
        return this.hasher.hash(file, encoding);
    }
}
