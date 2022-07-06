package com.alexander.scratchpad.crypto;

import java.util.Optional;

public class DESHash implements HashIdentifier {
    @Override
    public String extractPrefixHeader(String hash) {
        if (hash.startsWith(Hashes.md5)) {
            return Hashes.md5;
        }
        return "";
    }

    @Override
    public Optional<String> extractSalt(String hash) {
        //Look for three dollars, if only two then return empty
        return Optional.empty();
    }

    @Override
    public String extractHash(String hash) {
        //Dollar count (might be worth calculating at construction time?
        //If count is 2 then grab last section of the string
        //If count is 3 then grab last section of the string
        //Damn we're grabbing the last half anyway
        return null;
    }
}
