package io.github.lc.oss.commons.hashing;

import org.junit.jupiter.api.Test;

public class SHA2_512Test extends HasherTest {
    @Override
    protected Hasher getHasher() {
        return Hashes.SHA2_512;
    }

    @Test
    public void test_empty() {
        this.testEmpty("CF83E1357EEFB8BDF1542850D66D8007D620E4050B5715DC83F4A921D36CE9CE47D0D13C5D85F2B0FF8318D2877EEC2F63B931BD47417A81A538327AF927DA3E");
    }

    @Test
    public void test_data() {
        this.testData("C6EE9E33CF5C6715A1D148FD73F7318884B41ADCB916021E2BC0E800A5C5DD97F5142178F6AE88C8FDD98E1AFB0CE4C8D2C54B5F37B30B7DA1997BB33B0B8A31");
    }
}
