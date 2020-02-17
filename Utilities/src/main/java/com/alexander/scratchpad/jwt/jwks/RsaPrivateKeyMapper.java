package com.alexander.scratchpad.jwt.jwks;

import com.alexander.scratchpad.jwt.jwks.exception.KeyException;
import com.alexander.scratchpad.jwt.jwks.model.PrivateJsonWebKey;
import com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg;
import sun.security.rsa.RSAPrivateCrtKeyImpl;


public class RsaPrivateKeyMapper implements Mapper<PrivateJsonWebKey, RSAPrivateCrtKeyImpl> {


    @Override
    public PrivateJsonWebKey map(RSAPrivateCrtKeyImpl privateKey, String keyId, JwtAlg jwtAlg) {
        // perform a basic privateKey.getAlgorithm() vs jwtAlg.getKeyType
        if (privateKey == null) {
            throw new KeyException("Private Key cannot be null");
        }
        if (jwtAlg == null) {
            throw new KeyException("A " + JwtAlg.class.getName() + " is required");
        }
        if (! privateKey.getAlgorithm().equalsIgnoreCase(jwtAlg.getKeyType().name())) {
            throw new KeyException("Private Key algorithm [" + privateKey.getAlgorithm()
                    + "] does not match expected Jwt Algorithm " + jwtAlg.getKeyType().name()
                    + " of type " + jwtAlg.name());
        }
        if (keyId == null || keyId.trim().isEmpty()) {
            throw new KeyException("A key ID is required to use a JWKS");
        }
        return new PrivateJsonWebKey(
                jwtAlg.name(),
                keyId,
                privateKey.getAlgorithmId().getName(),
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
}
