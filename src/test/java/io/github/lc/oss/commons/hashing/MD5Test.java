package io.github.lc.oss.commons.hashing;

import org.junit.jupiter.api.Test;

public class MD5Test extends HasherTest {
    @Override
    protected Hasher getHasher() {
        return Hashes.MD5;
    }

    @Test
    public void test_empty() {
        this.testEmpty("D41D8CD98F00B204E9800998ECF8427E");
    }

    @Test
    public void test_data() {
        this.testData("0CBC6611F5540BD0809A388DC95A615B");
    }
}
