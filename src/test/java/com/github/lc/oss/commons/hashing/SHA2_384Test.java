package com.github.lc.oss.commons.hashing;

import org.junit.jupiter.api.Test;

public class SHA2_384Test extends HasherTest {
    @Override
    protected Hasher getHasher() {
        return Hashes.SHA2_384;
    }

    @Test
    public void test_empty() {
        this.testEmpty("38B060A751AC96384CD9327EB1B1E36A21FDB71114BE07434C0CC7BF63F6E1DA274EDEBFE76F65FBD51AD2F14898B95B");
    }

    @Test
    public void test_data() {
        this.testData("7B8F4654076B80EB963911F19CFAD1AAF4285ED48E826F6CDE1B01A79AA73FADB5446E667FC4F90417782C91270540F3");
    }
}
