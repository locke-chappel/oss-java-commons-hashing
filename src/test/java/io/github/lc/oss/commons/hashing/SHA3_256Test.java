package io.github.lc.oss.commons.hashing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SHA3_256Test extends HasherTest {
    @Override
    protected Hasher getHasher() {
        return Hashes.SHA3_256;
    }

    @Test
    public void test_empty() {
        this.testEmpty("A7FFC6F8BF1ED76651C14756A061D662F580FF4DE43B49FA82D80A4B80F8434A");
    }

    @Test
    public void test_data() {
        this.testData("C0A5CCA43B8AA79EB50E3464BC839DD6FD414FAE0DDF928CA23DCEBF8A8B8DD0");
    }

    @Test
    public void test_notSHA2() {
        Assertions.assertNotSame(Hashes.SHA2_256.hash("test"), Hashes.SHA3_256.hash("test"));
    }
}
