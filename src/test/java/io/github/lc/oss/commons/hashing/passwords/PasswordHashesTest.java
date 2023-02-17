package io.github.lc.oss.commons.hashing.passwords;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.lc.oss.commons.testing.AbstractTest;

public class PasswordHashesTest extends AbstractTest {
    @Test
    public void test_caching() {
        Set<PasswordHashes> expected = new HashSet<>(Arrays.asList(PasswordHashes.values()));
        Set<PasswordHasher> actual = PasswordHashes.all();

        Assertions.assertNotSame(expected, actual);
        Assertions.assertEquals(expected, actual);
        Assertions.assertTrue(expected.containsAll(actual));
        Assertions.assertTrue(actual.containsAll(expected));

        Assertions.assertTrue(PasswordHashes.hasName("PBKDF2"));
        Assertions.assertTrue(PasswordHashes.hasName("pbkdf2"));

        Assertions.assertSame(PasswordHashes.PBKDF2, PasswordHashes.byName("pBkDf2"));

        Assertions.assertSame(PasswordHashes.PBKDF2, PasswordHashes.tryParse("pbKDf2"));
    }

    @Test
    public void test_hash_null() {
        String hash = PasswordHashes.PBKDF2.hash((char[]) null);
        Assertions.assertTrue(PasswordHashes.PBKDF2.matches((char[]) null, hash));
        Assertions.assertTrue(PasswordHashes.PBKDF2.matches(new char[] { '\0' }, hash));
    }

    @Test
    public void test_hash_empty() {
        String hash = PasswordHashes.PBKDF2.hash("");
        Assertions.assertTrue(PasswordHashes.PBKDF2.matches("", hash));
        Assertions.assertTrue(PasswordHashes.PBKDF2.matches(new char[] { '\0' }, hash));
    }

    @Test
    public void test_hash() {
        String hash = PasswordHashes.PBKDF2.hash("test");
        Assertions.assertTrue(PasswordHashes.PBKDF2.matches("test", hash));
        Assertions.assertFalse(PasswordHashes.PBKDF2.matches("Test", hash));
    }
}
