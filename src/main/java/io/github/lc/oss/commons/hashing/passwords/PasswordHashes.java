package io.github.lc.oss.commons.hashing.passwords;

import java.util.Set;

import io.github.lc.oss.commons.util.TypedEnumCache;

public enum PasswordHashes implements PasswordHasher {
    PBKDF2(new PBKDF2("PBKDF2WithHmacSHA512", 120000, 512));

    private static final TypedEnumCache<PasswordHashes, PasswordHasher> CACHE = new TypedEnumCache<>(PasswordHashes.class, false);

    public static final Set<PasswordHasher> all() {
        return PasswordHashes.CACHE.values();
    }

    public static PasswordHasher byName(String name) {
        return PasswordHashes.CACHE.byName(name);
    }

    public static boolean hasName(String name) {
        return PasswordHashes.CACHE.hasName(name);
    }

    public static PasswordHasher tryParse(String name) {
        return PasswordHashes.CACHE.tryParse(name);
    }

    private final PasswordHasher impl;

    private PasswordHashes(PasswordHasher impl) {
        this.impl = impl;
    }

    @Override
    public String hash(char[] password) {
        return this.impl.hash(password);
    }

    @Override
    public boolean matches(char[] password, CharSequence hash) {
        return this.impl.matches(password, hash);
    }
}
