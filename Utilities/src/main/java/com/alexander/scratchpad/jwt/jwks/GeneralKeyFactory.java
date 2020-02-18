package com.alexander.scratchpad.jwt.jwks;

import com.alexander.scratchpad.jwt.jwks.model.KeyType;
import com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg;
import sun.security.rsa.RSAPrivateCrtKeyImpl;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;

public class GeneralKeyFactory {

    private final KeyPairGenerator rsaKeyPairGenerator;
    private final KeyFactory rsaKeyFactory;
    private final KeyGenerator hmacSha256KeyGenerator;
    private final KeyGenerator hmacSha384KeyGenerator;
    private final KeyGenerator hmacSha512KeyGenerator;


    //Perhaps switch to a map based initialisation of key generators?
    //Can reduce the need to use so many switch statements?
    public GeneralKeyFactory() throws NoSuchAlgorithmException {
        rsaKeyFactory = KeyFactory.getInstance(KeyType.RSA.name());
        rsaKeyPairGenerator = KeyPairGenerator.getInstance(KeyType.RSA.name());
        hmacSha256KeyGenerator = KeyGenerator.getInstance(KeyType.HmacSHA256.name());
        hmacSha384KeyGenerator = KeyGenerator.getInstance(KeyType.HmacSHA384.name());
        hmacSha512KeyGenerator = KeyGenerator.getInstance(KeyType.HmacSHA512.name());
    }

    public KeyPair getKeyPair(JwtAlg jwtAlg) {
        switch (jwtAlg.getKeyType()) {
            case RSA:
                return rsaKeyPairGenerator.generateKeyPair();
            case HmacSHA256:
            case HmacSHA384:
            case HmacSHA512:
                throw new RuntimeException( jwtAlg.getKeyType() + " is not an asymmetric algorithm");
            default:
                throw new RuntimeException( jwtAlg.getKeyType() + " not supported");
        }
    }

    public SecretKey getSecretKey(JwtAlg jwtAlg) {
        switch (jwtAlg.getKeyType()) {
            case RSA:
            case EC:
                throw new RuntimeException( jwtAlg.getKeyType() + " is not a symmetric algorithm");
            case HmacSHA256:
                return hmacSha256KeyGenerator.generateKey();
            case HmacSHA384:
                return hmacSha384KeyGenerator.generateKey();
            case HmacSHA512:
                return hmacSha512KeyGenerator.generateKey();
            default:
                throw new RuntimeException( jwtAlg.getKeyType() + " not a supported symmetric algorithm");
        }
    }

    public RSAPrivateCrtKeyImpl getKey(RSAPrivateCrtKeySpec spec) throws InvalidKeySpecException {
        return (RSAPrivateCrtKeyImpl)rsaKeyFactory.generatePrivate(spec);
    }

    public RSAPrivateCrtKeySpec getKeySpec(PrivateKey key) throws InvalidKeySpecException {
        return rsaKeyFactory.getKeySpec(key, RSAPrivateCrtKeySpec.class);
    }
}
