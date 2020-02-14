package com.alexander.scratchpad.jwt.jwks.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class JsonWebKey {

    public static final String ALGORITHM = "alg";
    public static final String KEY_ID = "kid";
    public static final String KEY_TYPE = "kty";
    public static final String PUBLIC_EXPONENT = "e";
    public static final String MODULUS = "n";
    public static final String USE = "use";

    @JsonProperty(ALGORITHM)
    private final String alg;
    @JsonProperty(KEY_ID)
    private final String kid;
    @JsonProperty(KEY_TYPE)
    private final String keyType;
    @JsonProperty(PUBLIC_EXPONENT)
    private final String exponent;
    @JsonProperty(MODULUS)
    private final String modulus;
    @JsonProperty(USE)
    private final String use;

    @JsonCreator
    public JsonWebKey(String alg,
                      String kid,
                      String keyType,
                      String exponent,
                      String modulus,
                      String use) {
        this.alg = alg;
        this.exponent = exponent;
        this.kid = kid;
        this.keyType = keyType;
        this.modulus = modulus;
        this.use = use;
    }
}