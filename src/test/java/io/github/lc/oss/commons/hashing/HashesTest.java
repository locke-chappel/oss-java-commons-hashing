package io.github.lc.oss.commons.hashing;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.lc.oss.commons.encoding.Encodings;
import io.github.lc.oss.commons.testing.AbstractTest;

public class HashesTest extends AbstractTest {
    @Test
    public void test_caching() {
        Set<Hashes> expected = new HashSet<>(Arrays.asList(Hashes.values()));
        Set<Hasher> actual = Hashes.all();

        Assertions.assertNotSame(expected, actual);
        Assertions.assertEquals(expected, actual);
        Assertions.assertTrue(expected.containsAll(actual));
        Assertions.assertTrue(actual.containsAll(expected));

        Assertions.assertTrue(Hashes.hasName("MD5"));
        Assertions.assertTrue(Hashes.hasName("mD5"));

        Assertions.assertSame(Hashes.SHA1, Hashes.byName("SHA1"));
        Assertions.assertSame(Hashes.SHA2_384, Hashes.byName("shA2_384"));

        Assertions.assertSame(Hashes.SHA2_512, Hashes.tryParse("SHA2_512"));
        Assertions.assertSame(Hashes.SHA2_512, Hashes.tryParse("sha2_512"));
    }

    @Test
    public void test_functions() {
        URI filePath = null;
        try {
            filePath = this.getClass().getClassLoader().getResource("Test").toURI();
        } catch (URISyntaxException ex) {
            Assertions.fail("Unexpected exception");
        }
        Assertions.assertTrue(Hashes.SHA1 instanceof Hasher);

        String result1 = Hashes.SHA1.hash("Test");
        String result2 = Hashes.SHA1.hash("Test".getBytes(StandardCharsets.UTF_8));
        String result3 = Hashes.SHA1.hash(Paths.get(filePath));
        Assertions.assertEquals("640AB2BAE07BEDC4C163F679A746F7AB7FB5D1FA", result1);
        Assertions.assertEquals("640AB2BAE07BEDC4C163F679A746F7AB7FB5D1FA", result2);
        Assertions.assertEquals("640AB2BAE07BEDC4C163F679A746F7AB7FB5D1FA", result3);

        String result4 = Hashes.SHA1.hash("Test", Encodings.Base64);
        String result5 = Hashes.SHA1.hash("Test".getBytes(StandardCharsets.UTF_8), Encodings.Base64);
        String result6 = Hashes.SHA1.hash(Paths.get(filePath), Encodings.Base64);
        Assertions.assertEquals("ZAqyuuB77cTBY/Z5p0b3q3+10fo=", result4);
        Assertions.assertEquals("ZAqyuuB77cTBY/Z5p0b3q3+10fo=", result5);
        Assertions.assertEquals("ZAqyuuB77cTBY/Z5p0b3q3+10fo=", result6);
    }
}
