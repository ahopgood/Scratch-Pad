package com.alexander.scratchpad.jwt.jwks.model;

import com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class PrivateJsonWebKeyTest {

    private final ObjectMapper mapper = new ObjectMapper();

    private static final String privateKeyJson = "{\"kty\":\"RSA\"," +
            "\"n\":\"1234\"," +
            "\"e\":\"AQAB\"," +
            "\"d\":\"5678\"," +
            "\"p\":\"5\"," +
            "\"q\":\"7\"," +
            "\"dp\":\"10\"," +
            "\"dq\":\"11\"," +
            "\"qi\":\"12\"," +
            "\"alg\":\"RS256\"," +
            "\"kid\":\"2011-04-29\"}";

    // See https://en.wikipedia.org/wiki/RSA_(cryptosystem)#Example
    // for a break down of the values dp dp anc qi for the chinese remainder theorem to speed up computation.

    @Test
    void testFromJson() throws IOException {
        JsonNode output = mapper.readValue(privateKeyJson, JsonNode.class);
        //Public key values
        assertThat(output.get(PrivateJsonWebKey.ALGORITHM).textValue()).isEqualTo("RS256");
        assertThat(output.get(PrivateJsonWebKey.KEY_ID).textValue()).isEqualTo("2011-04-29");
        assertThat(output.get(PrivateJsonWebKey.KEY_TYPE).textValue()).isEqualTo("RSA");
        assertThat(output.get(PrivateJsonWebKey.PUBLIC_EXPONENT).textValue()).isEqualTo("AQAB");
        assertThat(output.get(PrivateJsonWebKey.MODULUS).textValue()).isEqualTo("1234");
        // Private key values
        assertThat(output.get(PrivateJsonWebKey.PRIME_P).textValue()).isEqualTo("5");
        assertThat(output.get(PrivateJsonWebKey.PRIME_Q).textValue()).isEqualTo("7");
        assertThat(output.get(PrivateJsonWebKey.PRIVATE_EXPONENT).textValue()).isEqualTo("5678");
        assertThat(output.get(PrivateJsonWebKey.DP).textValue()).isEqualTo("10");
        assertThat(output.get(PrivateJsonWebKey.DQ).textValue()).isEqualTo("11");
        assertThat(output.get(PrivateJsonWebKey.QI).textValue()).isEqualTo("12");
    }

    @Test
    void testToJson() throws IOException {
        PrivateJsonWebKey webKey = PrivateJsonWebKey.builder()
                .alg(JwtAlg.RS256.toString())
                .kid("1")
                .keyType(KeyType.RSA.toString())
                .publicExponent("1234")
                .modulus("3")
                .primeP("5")
                .primeQ("7")
                .privateExponent("5678")
                .dp("10")
                .dq("11")
                .qi("12")
                .build();

        String jsonString = mapper.writeValueAsString(webKey);
        JsonNode output = mapper.readValue(jsonString, JsonNode.class);
        //Public key values
        assertThat(output.get(PrivateJsonWebKey.ALGORITHM).textValue()).isEqualTo("RS256");
        assertThat(output.get(PrivateJsonWebKey.KEY_ID).textValue()).isEqualTo("1");
        assertThat(output.get(PrivateJsonWebKey.KEY_TYPE).textValue()).isEqualTo("RSA");
        assertThat(output.get(PrivateJsonWebKey.PUBLIC_EXPONENT).textValue()).isEqualTo("1234");
        assertThat(output.get(PrivateJsonWebKey.MODULUS).textValue()).isEqualTo("3");
        // Private key values
        assertThat(output.get(PrivateJsonWebKey.PRIME_P).textValue()).isEqualTo("5");
        assertThat(output.get(PrivateJsonWebKey.PRIME_Q).textValue()).isEqualTo("7");
        assertThat(output.get(PrivateJsonWebKey.PRIVATE_EXPONENT).textValue()).isEqualTo("5678");
        assertThat(output.get(PrivateJsonWebKey.DP).textValue()).isEqualTo("10");
        assertThat(output.get(PrivateJsonWebKey.DQ).textValue()).isEqualTo("11");
        assertThat(output.get(PrivateJsonWebKey.QI).textValue()).isEqualTo("12");
    }
}
