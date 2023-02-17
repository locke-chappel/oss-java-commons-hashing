package io.github.lc.oss.commons.hashing;

class CommonHasher extends AbstractHasher {
    private final String hashName;

    public CommonHasher(String hashName) {
        this.hashName = hashName;
    }

    @Override
    protected String getHashName() {
        return this.hashName;
    }
}
