package com.alexander.scratchpad.crypto;

import java.util.Optional;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DESHashTest {

    private DESHash desHash = new DESHash();

    private final String hashNoSalt = "$1$4xO31fqM$M2KOxmoUs2hMUtX8ySM6J1";
    private final String hash = "$1$xyz$jAlmRpcFe.aiPOIXET9GG/";

    @Test
    void testExtractPrefixHeader_givenDesNoSalt() {
        assertThat(desHash.extractPrefixHeader(hashNoSalt))
            .isEqualTo(Hashes.md5);
    }

    @Test
    void testExtractPrefixHeader_givenDesWithSalt() {
        assertThat(desHash.extractPrefixHeader(hash))
            .isEqualTo(Hashes.md5);
    }

    @Test
    void testExtractSalt_givenDesNoSalt() {
        assertThat(desHash.extractSalt(hashNoSalt)).isEmpty();
    }

    @Test
    void testExtractSalt_givenDesWithSalt() {
        assertThat(desHash.extractSalt(hash))
            .isEqualTo("xyz");
    }

    @Test
    void extractHash_givenDesNoSalt() {
        assertThat(desHash.extractHash(hash))
            .isEqualTo("4xO31fqM$M2KOxmoUs2hMUtX8ySM6J1");
    }

    @Test
    void extractHash_givenDesWithSalt() {
        assertThat(desHash.extractHash(hash))
            .isEqualTo("jAlmRpcFe.aiPOIXET9GG/");
    }
}
