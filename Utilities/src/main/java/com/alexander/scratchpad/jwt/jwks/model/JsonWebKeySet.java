package com.alexander.scratchpad.jwt.jwks.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;


//@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"}, justification = "Model Object from external JWK source")
@Getter
public class JsonWebKeySet {

    private final JsonWebKey[] keys;

    @JsonCreator
    public JsonWebKeySet(@JsonProperty(value = "keys") JsonWebKey[] keys) {
        this.keys = Arrays.copyOf(keys, keys.length);
    }
}
