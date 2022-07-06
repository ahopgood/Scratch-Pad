package com.alexander.scratchpad.crypto;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SHAHashTest {

    private SHAHash shaHash = new SHAHash();

    @Nested
    class givenSha256 {

        private final String hashNoSalt = "$5$hM7nLDaDAWsC/4i8$qQvZsvi9.5CTscAeulj6hKXV9YN5gFnbGiKEcYePGo4";
        private final String hash = "$5$xyz$lAcshDm5XTvQJHoPQa.sg9AN9hLBeuR6tY5HTWkGyxA";

        @Test
        void testExtractPrefixHeader_givenShaNoSalt() {
            assertThat(shaHash.extractPrefixHeader(hashNoSalt))
                .isEqualTo(Hashes.sha256);
        }

        @Test
        void testExtractPrefixHeader_givenShaWithSalt() {
            assertThat(shaHash.extractPrefixHeader(hash))
                .isEqualTo(Hashes.sha256);
        }

        @Test
        void testExtractSalt_givenShaNoSalt() {
            assertThat(shaHash.extractSalt(hashNoSalt)).isEmpty();
        }

        @Test
        void testExtractSalt_givenShaWithSalt() {
            assertThat(shaHash.extractSalt(hash))
                .isEqualTo("xyz");
        }

        @Test
        void extractHash_givenShaNoSalt() {
            assertThat(shaHash.extractHash(hash))
                .isEqualTo("hM7nLDaDAWsC/4i8$qQvZsvi9.5CTscAeulj6hKXV9YN5gFnbGiKEcYePGo4");
        }

        @Test
        void extractHash_givenShaWithSalt() {
            assertThat(shaHash.extractHash(hash))
                .isEqualTo("lAcshDm5XTvQJHoPQa.sg9AN9hLBeuR6tY5HTWkGyxA");
        }
    }

    @Nested
    class givenSha512 {
        private final String hashNoSalt = "$6$Y6V3HlDAFRzFKLLn$1qc0cNIlQl8LZEKUB8EEFdLJq/F/B/SaUqn/upnDhYBbMHpGfoKf9toE65XZ5r5au9MvXXbqJtK7wUBnyip4W.";
        private final String hash = "$6$xyz$rjarwc/BNZWcH6B31aAXWo1942.i7rCX5AT/oxALL5gCznYVGKh6nycQVZiHDVbnbu0BsQyPfBgqYveKcCgOE0";

        @Test
        void testExtractPrefixHeader_givenShaNoSalt() {
            assertThat(shaHash.extractPrefixHeader(hashNoSalt))
                .isEqualTo(Hashes.sha512);
        }

        @Test
        void testExtractPrefixHeader_givenShaWithSalt() {
            assertThat(shaHash.extractPrefixHeader(hash))
                .isEqualTo(Hashes.sha512);
        }

        @Test
        void testExtractSalt_givenShaNoSalt() {
            assertThat(shaHash.extractSalt(hashNoSalt)).isEmpty();
        }

        @Test
        void testExtractSalt_givenShaWithSalt() {
            assertThat(shaHash.extractSalt(hash))
                .isEqualTo("xyz");
        }

        @Test
        void extractHash_givenShaNoSalt() {
            assertThat(shaHash.extractHash(hash))
                .isEqualTo("Y6V3HlDAFRzFKLLn$1qc0cNIlQl8LZEKUB8EEFdLJq/F/B/SaUqn/upnDhYBbMHpGfoKf9toE65XZ5r5au9MvXXbqJtK7wUBnyip4W.");
        }

        @Test
        void extractHash_givenShaWithSalt() {
            assertThat(shaHash.extractHash(hash))
                .isEqualTo("rjarwc/BNZWcH6B31aAXWo1942.i7rCX5AT/oxALL5gCznYVGKh6nycQVZiHDVbnbu0BsQyPfBgqYveKcCgOE0");
        }
    }
}
