package io.github.lc.oss.commons.hashing.passwords;

public interface PasswordHasher {
    default String hash(CharSequence password) {
        return this.hash(password.toString().toCharArray());
    }

    String hash(char[] password);

    default boolean matches(CharSequence password, CharSequence hash) {
        return this.matches(password.toString().toCharArray(), hash);
    }

    boolean matches(char[] password, CharSequence hash);
}
