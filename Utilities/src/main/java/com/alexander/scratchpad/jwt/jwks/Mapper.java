package com.alexander.scratchpad.jwt.jwks;

import com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg;

public interface Mapper<U, T> {

    U map(T t, String keyId, JwtAlg jwtAlg);

    T map(U y);
}
