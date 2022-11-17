package com.github.lc.oss.commons.hashing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SHA3_384Test extends HasherTest {
    @Override
    protected Hasher getHasher() {
        return Hashes.SHA3_384;
    }

    @Test
    public void test_empty() {
        this.testEmpty("0C63A75B845E4F7D01107D852E4C2485C51A50AAAA94FC61995E71BBEE983A2AC3713831264ADB47FB6BD1E058D5F004");
    }

    @Test
    public void test_data() {
        this.testData("DA73BFCBA560692A019F52C37DE4D5E3AB49CA39C6A75594E3C39D805388C4DE9D0FF3927EB9E197536F5B0B3A515F0A");
    }

    @Test
    public void test_notSHA2() {
        Assertions.assertNotSame(Hashes.SHA2_384.hash("test"), Hashes.SHA3_384.hash("test"));
    }
}
