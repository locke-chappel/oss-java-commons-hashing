package com.github.lc.oss.commons.hashing;

import org.junit.jupiter.api.Test;

public class SHA1Test extends HasherTest {
    @Override
    protected Hasher getHasher() {
        return Hashes.SHA1;
    }

    @Test
    public void test_empty() {
        this.testEmpty("DA39A3EE5E6B4B0D3255BFEF95601890AFD80709");
    }

    @Test
    public void test_data() {
        this.testData("640AB2BAE07BEDC4C163F679A746F7AB7FB5D1FA");
    }
}
