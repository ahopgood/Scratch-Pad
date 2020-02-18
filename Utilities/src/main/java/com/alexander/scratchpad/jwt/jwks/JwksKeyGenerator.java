package com.alexander.scratchpad.jwt.jwks;

import com.alexander.scratchpad.jwt.jwks.model.PrivateJsonWebKey;
import com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class JwksKeyGenerator {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, JsonProcessingException {
        GeneralKeyFactory factory = new GeneralKeyFactory();
        RsaPrivateKeyMapper mapper = new RsaPrivateKeyMapper();

        KeyPair pair = factory.getKeyPair(JwtAlg.RS256);

        PrivateJsonWebKey privateJsonWebKey = mapper.map(factory.getKeySpec(pair.getPrivate()), "1234", JwtAlg.RS256);
        ObjectMapper jsonMapper = new ObjectMapper();


        System.out.println(jsonMapper.writeValueAsString(privateJsonWebKey));
    }
}
