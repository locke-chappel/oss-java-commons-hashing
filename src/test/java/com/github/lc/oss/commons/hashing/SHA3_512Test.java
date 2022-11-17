package com.github.lc.oss.commons.hashing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SHA3_512Test extends HasherTest {
    @Override
    protected Hasher getHasher() {
        return Hashes.SHA3_512;
    }

    @Test
    public void test_empty() {
        this.testEmpty("A69F73CCA23A9AC5C8B567DC185A756E97C982164FE25859E0D1DCC1475C80A615B2123AF1F5F94C11E3E9402C3AC558F500199D95B6D3E301758586281DCD26");
    }

    @Test
    public void test_data() {
        this.testData("301BB421C971FBB7ED01DCC3A9976CE53DF034022BA982B97D0F27D48C4F03883AABF7C6BC778AA7C383062F6823045A6D41B8A720AFBB8A9607690F89FBE1A7");
    }

    @Test
    public void test_notSHA2() {
        Assertions.assertNotSame(Hashes.SHA2_512.hash("test"), Hashes.SHA3_512.hash("test"));
    }
}
