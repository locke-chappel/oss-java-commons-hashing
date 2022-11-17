package com.github.lc.oss.commons.hashing.passwords;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.lc.oss.commons.testing.AbstractTest;

public class PBKDF2Test extends AbstractTest {
    @Test
    public void test_basAlgorithm() {
        PBKDF2 p = new PBKDF2("bad", 1, 512);

        try {
            p.hash("test");
            Assertions.fail("Expected exception");
        } catch (RuntimeException ex) {
            Assertions.assertEquals("Error hashing password", ex.getMessage());
        }
    }
}
