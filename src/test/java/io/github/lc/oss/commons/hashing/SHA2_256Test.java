package io.github.lc.oss.commons.hashing;

import org.junit.jupiter.api.Test;

public class SHA2_256Test extends HasherTest {
    @Override
    protected Hasher getHasher() {
        return Hashes.SHA2_256;
    }

    @Test
    public void test_empty() {
        this.testEmpty("E3B0C44298FC1C149AFBF4C8996FB92427AE41E4649B934CA495991B7852B855");
    }

    @Test
    public void test_data() {
        this.testData("532EAABD9574880DBF76B9B8CC00832C20A6EC113D682299550D7A6E0F345E25");
    }
}
