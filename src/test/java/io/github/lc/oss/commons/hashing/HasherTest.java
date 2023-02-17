package io.github.lc.oss.commons.hashing;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.lc.oss.commons.testing.AbstractTest;

public abstract class HasherTest extends AbstractTest {
    protected abstract Hasher getHasher();

    protected Path getPath() {
        URI filePath = null;
        try {
            filePath = this.getClass().getClassLoader().getResource("Test").toURI();
        } catch (URISyntaxException ex) {
            Assertions.fail("Unexpected exception");
        }
        return Paths.get(filePath);
    }

    @Test
    public void test_null() {
        try {
            this.getHasher().hash((byte[]) null);
            Assertions.fail("Expected Excetion");
        } catch (NullPointerException ex) {
            // pass
        }

        try {
            this.getHasher().hash((String) null);
            Assertions.fail("Expected Excetion");
        } catch (NullPointerException ex) {
            // pass
        }

        try {
            this.getHasher().hash((Path) null);
            Assertions.fail("Expected Excetion");
        } catch (NullPointerException ex) {
            // pass
        }
    }

    protected void testEmpty(String expected) {
        Assertions.assertEquals(expected, this.getHasher().hash(new byte[0]));
        Assertions.assertEquals(expected, this.getHasher().hash(""));
    }

    protected void testData(String expected) {
        Assertions.assertEquals(expected, this.getHasher().hash("Test".getBytes(StandardCharsets.UTF_8)));
        Assertions.assertEquals(expected, this.getHasher().hash("Test"));
        Assertions.assertEquals(expected, this.getHasher().hash(this.getPath()));
    }
}
