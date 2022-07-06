package com.alexander.scratchpad.crypto;

import java.util.Optional;

public class SHAHash implements HashIdentifier {
    @Override
    public String extractPrefixHeader(String hash) {
        return null;
    }

    @Override
    public Optional<String> extractSalt(String hash) {
        return Optional.empty();
    }

    @Override
    public String extractHash(String hash) {
        return null;
    }
}
