package com.alexander.scratchpad.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Map;

public class JwtFactory {

    //Generate String
        //Convert object to json map - fields to values
        //Set map to claims in the jwtBuilder
        //Sign - Choose algo & Convert secret string to bytes
        //Compact

    public static String generateJwtString(Object token, String secret) throws JwtEncodingException {
        if (token == null){
            throw new JwtEncodingException("Token is null");
        }
        if (secret == null || secret.isEmpty()){
            throw new JwtEncodingException("Secret is empty or null");
        }

        Map<String, Object> tokenMap = new ObjectMapper().convertValue(token, Map.class);

        return Jwts.builder()
                .setClaims(tokenMap)
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public static Object decodeJwtString(String token, String secret){
        return null;
    }
}
