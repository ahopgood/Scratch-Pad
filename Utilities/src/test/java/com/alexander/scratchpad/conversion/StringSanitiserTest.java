package com.alexander.scratchpad.conversion;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringSanitiserTest {

    private static final String OUTPUT = "testtest";

    @Test
    void testSanitise() {
        assertThat(StringSanitiser.sanitise(OUTPUT)).isEqualTo(OUTPUT);
    }

    @Test
    void testSanitise_newline() {
        assertThat(StringSanitiser.sanitise("test\ntest")).isEqualTo(OUTPUT);
    }

    @Test
    void testSanitise_carriageReturn() {
        assertThat(StringSanitiser.sanitise("test\rtest")).isEqualTo(OUTPUT);
    }

    @Test
    void testSanitise_newlineCarriageReturn() {
        assertThat(StringSanitiser.sanitise("test\n\rtest")).isEqualTo(OUTPUT);
    }
}
