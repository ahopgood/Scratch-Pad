package com.alexander.scratchpad.jwt.jwks.model.algorithms;

import com.alexander.scratchpad.jwt.jwks.model.KeyType;

public enum JwtAlg {
    RS256, RS512, RS384, HS256, HS384, HS512, ES256, ES384, ES512, PS256, PS384, PS512;

    public KeyType getKeyType() {
        switch (this) {
            case RS256:
            case RS384:
            case RS512:
                return KeyType.RSA;
            case HS256:
                return KeyType.HmacSHA256;
            case HS384:
                return KeyType.HmacSHA384;
            case HS512:
                return KeyType.HmacSHA512;
            case ES256:
            case ES384:
            case ES512:
            case PS256:
            case PS384:
            case PS512:
            default:
                throw new RuntimeException(this + " algorithm type does not have a supported " + KeyType.class.getName());
        }
    }
}
