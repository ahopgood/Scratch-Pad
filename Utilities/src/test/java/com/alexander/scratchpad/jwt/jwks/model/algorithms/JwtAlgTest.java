package com.alexander.scratchpad.jwt.jwks.model.algorithms;


import com.alexander.scratchpad.jwt.jwks.model.KeyType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.alexander.scratchpad.jwt.jwks.model.KeyType.EC;
import static com.alexander.scratchpad.jwt.jwks.model.KeyType.HmacSHA256;
import static com.alexander.scratchpad.jwt.jwks.model.KeyType.HmacSHA384;
import static com.alexander.scratchpad.jwt.jwks.model.KeyType.HmacSHA512;
import static com.alexander.scratchpad.jwt.jwks.model.KeyType.RSA;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.ES256;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.ES384;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.ES512;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.HS256;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.HS384;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.HS512;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.PS256;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.PS384;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.PS512;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.RS256;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.RS384;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.RS512;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtAlgTest {

    @ParameterizedTest
    @MethodSource("createAlgorithmsAndKeyTypes")
    void verifyKeyType(JwtAlg alg, KeyType expectedKeyType, Class<Exception> e) {
        if (e == null) {
            assertThat(alg.getKeyType()).isEqualTo(expectedKeyType);
        } else {
            assertThrows(RuntimeException.class, alg::getKeyType);
        }
    }

    private static Stream<Arguments> createAlgorithmsAndKeyTypes() {
        return Stream.of(
                Arguments.of(RS256, RSA, null),
                Arguments.of(RS384, RSA, null),
                Arguments.of(RS512, RSA,  null),
                Arguments.of(HS256, HmacSHA256,  null),
                Arguments.of(HS384, HmacSHA384,  null),
                Arguments.of(HS512, HmacSHA512,  null),
                Arguments.of(ES256, EC, null),
                Arguments.of(ES384, EC, null),
                Arguments.of(ES512, EC, null),
                Arguments.of(PS256, null, RuntimeException.class),
                Arguments.of(PS384, null, RuntimeException.class),
                Arguments.of(PS512, null, RuntimeException.class)
        );
    }

    @Test
    void checkForEnumIncrease() {
        assertThat(JwtAlg.values().length).isEqualTo(12);
    }
}