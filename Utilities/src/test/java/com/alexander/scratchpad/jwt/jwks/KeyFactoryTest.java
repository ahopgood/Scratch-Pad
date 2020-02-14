package com.alexander.scratchpad.jwt.jwks;


import com.alexander.scratchpad.jwt.jwks.model.KeyType;
import com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

import static com.alexander.scratchpad.jwt.jwks.model.KeyType.HmacSHA256;
import static com.alexander.scratchpad.jwt.jwks.model.KeyType.HmacSHA384;
import static com.alexander.scratchpad.jwt.jwks.model.KeyType.HmacSHA512;
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
import static com.alexander.scratchpad.jwt.jwks.model.KeyType.RSA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KeyFactoryTest {

    KeyFactory generator = new KeyFactory();

    public KeyFactoryTest() throws NoSuchAlgorithmException {
    }

    @ParameterizedTest
    @MethodSource("createJwtAndAlg")
    public void getPrivateKeyPair(JwtAlg alg, KeyType expectedAlgorithm, Class<Exception> e) throws NoSuchAlgorithmException {
        if (e == null) {
            assertThat(generator.getKeyPair(alg).getPrivate().getAlgorithm()).isEqualTo(expectedAlgorithm.name());
        } else {
            assertThrows(RuntimeException.class, () -> generator.getKeyPair(alg));
        }
    }

    private static Stream<Arguments> createJwtAndAlg() {
        return Stream.of(
                Arguments.of(RS256, RSA, null),
                Arguments.of(RS384, RSA, null),
                Arguments.of(RS512, RSA, null),
                Arguments.of(HS256, null, RuntimeException.class),
                Arguments.of(HS384, null, RuntimeException.class),
                Arguments.of(HS512, null, RuntimeException.class),
                Arguments.of(ES256, null, RuntimeException.class),
                Arguments.of(ES384, null, RuntimeException.class),
                Arguments.of(ES512, null, RuntimeException.class),
                Arguments.of(PS256, null, RuntimeException.class),
                Arguments.of(PS384, null, RuntimeException.class),
                Arguments.of(PS512, null, RuntimeException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("createSymmetricJwtAndAlg")
    public void testGetSecretKey(JwtAlg alg, KeyType keyType, Class<Exception> e) throws NoSuchAlgorithmException {
        if (e == null) {
            assertThat(generator.getSecretKey(alg).getAlgorithm()).isEqualTo(keyType.name());
        } else {
            assertThrows(RuntimeException.class, () -> generator.getSecretKey(alg));
        }
    }

    private static Stream<Arguments> createSymmetricJwtAndAlg() {
        return Stream.of(
                Arguments.of(RS256, null, RuntimeException.class),
                Arguments.of(RS384, null, RuntimeException.class),
                Arguments.of(RS512, null, RuntimeException.class),
                Arguments.of(HS256, HmacSHA256, null),
                Arguments.of(HS384, HmacSHA384, null),
                Arguments.of(HS512, HmacSHA512, null),
                Arguments.of(ES256, null, RuntimeException.class),
                Arguments.of(ES384, null, RuntimeException.class),
                Arguments.of(ES512, null, RuntimeException.class),
                Arguments.of(PS256, null, RuntimeException.class),
                Arguments.of(PS384, null, RuntimeException.class),
                Arguments.of(PS512, null, RuntimeException.class)
        );
    }
}