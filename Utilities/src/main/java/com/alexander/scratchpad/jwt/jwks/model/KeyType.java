package com.alexander.scratchpad.jwt.jwks.model;

/**
 * Enum that maps to algorithms used by the {@link java.security.KeyPairGenerator} and {@link javax.crypto.KeyGenerator}
 */
public enum KeyType {
    RSA, HmacSHA256, HmacSHA384, HmacSHA512, EC;
}

