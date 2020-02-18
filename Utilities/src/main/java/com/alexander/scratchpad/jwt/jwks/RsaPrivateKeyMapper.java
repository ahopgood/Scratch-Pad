package com.alexander.scratchpad.jwt.jwks;

import com.alexander.scratchpad.jwt.jwks.exception.KeyException;
import com.alexander.scratchpad.jwt.jwks.model.PrivateJsonWebKey;
import com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg;

import java.math.BigInteger;
import java.security.spec.RSAPrivateCrtKeySpec;


public class RsaPrivateKeyMapper implements Mapper<PrivateJsonWebKey, RSAPrivateCrtKeySpec> {


    @Override
    public PrivateJsonWebKey map(RSAPrivateCrtKeySpec privateKey, String keyId, JwtAlg jwtAlg) {
        // perform a basic privateKey.getAlgorithm() vs jwtAlg.getKeyType
        if (privateKey == null) {
            throw new KeyException("Private Key cannot be null");
        }
        if (jwtAlg == null) {
            throw new KeyException("A " + JwtAlg.class.getName() + " is required");
        }
        if (keyId == null || keyId.trim().isEmpty()) {
            throw new KeyException("A key ID is required to use a JWKS");
        }
        return new PrivateJsonWebKey(
                jwtAlg.name(),
                keyId,
                "RSA",
                privateKey.getPublicExponent().toString(),
                privateKey.getModulus().toString(),
                privateKey.getPrimeP().toString(),
                privateKey.getPrimeQ().toString(),
                privateKey.getPrivateExponent().toString(),
                privateKey.getPrimeExponentQ().toString(),
                privateKey.getPrimeExponentP().toString(),
                privateKey.getCrtCoefficient().toString()
        );
    }

    @Override
    public RSAPrivateCrtKeySpec map(PrivateJsonWebKey privateJsonWebKey) {
        RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(
                new BigInteger(privateJsonWebKey.getModulus()),
                new BigInteger(privateJsonWebKey.getPublicExponent()),
                new BigInteger(privateJsonWebKey.getPrivateExponent()),
                new BigInteger(privateJsonWebKey.getPrimeP()),
                new BigInteger(privateJsonWebKey.getPrimeQ()),
                new BigInteger(privateJsonWebKey.getDp()),
                new BigInteger(privateJsonWebKey.getDq()),
                new BigInteger(privateJsonWebKey.getQi())

        );
        return keySpec;
    }
}
