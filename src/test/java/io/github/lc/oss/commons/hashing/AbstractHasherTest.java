package io.github.lc.oss.commons.hashing;

import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.lc.oss.commons.testing.AbstractTest;

public class AbstractHasherTest extends AbstractTest {
    private static class BadDigestHasher extends AbstractHasher {
        @Override
        protected String getHashName() {
            return "junk";
        }
    }

    @Test
    public void test_badDigest() {
        AbstractHasher hasher = new BadDigestHasher();

        try {
            hasher.hash("data");
            Assertions.fail("Expected exception");
        } catch (RuntimeException ex) {
            Assertions.assertEquals("Unknown digest: junk", ex.getMessage());
        }
    }

    @Test
    public void test_badPath() {
        AbstractHasher hasher = new CommonHasher("MD5");

        try {
            hasher.hash(Paths.get("C://nonexistent"));
            Assertions.fail("Expected exception");
        } catch (RuntimeException ex) {
            Assertions.assertEquals("Error hashing file", ex.getMessage());
        }
    }
}
