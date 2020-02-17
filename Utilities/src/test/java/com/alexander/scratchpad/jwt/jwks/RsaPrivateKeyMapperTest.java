package com.alexander.scratchpad.jwt.jwks;

import com.alexander.scratchpad.jwt.jwks.exception.KeyException;
import com.alexander.scratchpad.jwt.jwks.model.PrivateJsonWebKey;
import com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg;
import org.junit.jupiter.api.Test;
import sun.security.rsa.RSAPrivateCrtKeyImpl;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RsaPrivateKeyMapperTest {

    private final RsaPrivateKeyMapper mapper = new RsaPrivateKeyMapper();
    private final KeyFactory factory = new KeyFactory();

    private final KeyPair keyPair = factory.getKeyPair(JwtAlg.RS256);
    private final RSAPrivateCrtKeyImpl privateKey = (RSAPrivateCrtKeyImpl)keyPair.getPrivate();


    private final BigInteger primeP = privateKey.getPrimeP();
    private final BigInteger primeQ = privateKey.getPrimeQ();
    private final BigInteger modulus = privateKey.getModulus();
    private final BigInteger publicExponent = privateKey.getPublicExponent();
    private final BigInteger privateExponent = privateKey.getPrivateExponent();
    private final BigInteger primeExP = privateKey.getPrimeExponentP();
    private final BigInteger primeExQ = privateKey.getPrimeExponentQ();

    RsaPrivateKeyMapperTest() throws NoSuchAlgorithmException { }

    @Test
    void testMap_givenNullPrivateKey_thenThrowException() {
        assertThrows(KeyException.class, () -> mapper.map(null, "1234", JwtAlg.RS256));
    }

    @Test
    void testMap_givenNullJwtAlg_thenThrowException() {
        assertThrows(KeyException.class, () -> mapper.map(privateKey, "1234", null));
    }

    @Test
    void testMap_givenRSAPrivateKey_whenJwtAlgIsES256_thenThrowException() {
        assertThrows(KeyException.class, () -> mapper.map((RSAPrivateCrtKeyImpl) factory.getKeyPair(JwtAlg.RS256).getPrivate(), "1234", JwtAlg.ES256));
    }
    @Test
    void testMap_givenRSAPrivateCrtKeyImpl() {
        PrivateJsonWebKey jsonKey = mapper.map(privateKey, "1234", JwtAlg.RS256);
        assertThat(jsonKey.getAlg()).isEqualTo("RS256");
        assertThat(jsonKey.getKeyType()).isEqualTo("RSA");
        assertThat(jsonKey.getPrimeP()).isEqualTo(primeP.toString());
        assertThat(jsonKey.getPrimeQ()).isEqualTo(primeQ.toString());
        assertThat(jsonKey.getModulus()).isEqualTo(modulus.toString());
        assertThat(jsonKey.getKid()).isEqualTo("1234");
        assertThat(jsonKey.getPrivateExponent()).isEqualTo(privateExponent.toString());
        assertThat(jsonKey.getPublicExponent()).isEqualTo(publicExponent.toString());
        assertThat(jsonKey.getDp()).isEqualTo(primeExP.toString());
        assertThat(jsonKey.getDq()).isEqualTo(primeExQ.toString());
        assertThat(jsonKey.getQi()).isEqualTo(privateKey.getCrtCoefficient().toString());
    }
}