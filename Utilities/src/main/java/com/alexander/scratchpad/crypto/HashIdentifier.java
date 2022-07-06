package com.alexander.scratchpad.crypto;

import java.util.Optional;

public interface HashIdentifier {

    String extractPrefixHeader(String hash);
    Optional<String> extractSalt(String hash);
    String extractHash(String hash);
}
