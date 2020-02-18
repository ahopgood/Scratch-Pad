package com.alexander.scratchpad.jwt.jwks.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * A representation of a Private RSA key according to the JSON Web Key RFC {@link https://tools.ietf.org/html/rfc7517#page-26}
 */
@Builder
@Getter
@EqualsAndHashCode
public class PrivateJsonWebKey {
    // Constants for json field names, public to aid tests.
    public static final String ALGORITHM = "alg";
    public static final String KEY_ID = "kid";
    public static final String KEY_TYPE = "kty";
    public static final String PUBLIC_EXPONENT = "e";
    public static final String MODULUS = "n";
    public static final String PRIME_P = "p";
    public static final String PRIME_Q = "q";
    public static final String PRIVATE_EXPONENT = "d";
    public static final String DQ = "dq";
    public static final String DP = "dp";
    //Chinese Remainder Theorem Coefficient
    public static final String QI = "qi";

    @JsonProperty(ALGORITHM)
    private final String alg;
    @JsonProperty(KEY_ID)
    private final String kid;
    @JsonProperty(KEY_TYPE)
    private final String keyType;
    @JsonProperty(PUBLIC_EXPONENT)
    private final String publicExponent;
    @JsonProperty(MODULUS)
    private final String modulus;
    @JsonProperty(PRIME_P)
    private final String primeP;
    @JsonProperty(PRIME_Q)
    private final String primeQ;
    @JsonProperty(PRIVATE_EXPONENT)
    private final String privateExponent;
    @JsonProperty(DQ)
    private final String dq;
    @JsonProperty(DP)
    private final String dp;
    @JsonProperty(QI)
    private final String qi;

    @JsonCreator
    public PrivateJsonWebKey(String alg,
                             String kid,
                             String keyType,
                             String publicExponent,
                             String modulus,
                             String primeP,
                             String primeQ,
                             String privateExponent,
                             String dq,
                             String dp,
                             String qi) {
        this.alg = alg;
        this.kid = kid;
        this.keyType = keyType;
        this.publicExponent = publicExponent;
        this.modulus = modulus;
        this.primeP = primeP;
        this.primeQ = primeQ;
        this.privateExponent = privateExponent;
        this.dq = dq;
        this.dp = dp;
        this.qi = qi;
    }

}
