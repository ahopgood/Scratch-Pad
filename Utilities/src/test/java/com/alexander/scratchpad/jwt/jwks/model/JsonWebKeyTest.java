package com.alexander.scratchpad.jwt.jwks.model;

import com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import org.junit.jupiter.api.Test;


import static com.alexander.scratchpad.jwt.jwks.model.JsonWebKey.ALGORITHM;
import static com.alexander.scratchpad.jwt.jwks.model.JsonWebKey.KEY_ID;
import static com.alexander.scratchpad.jwt.jwks.model.JsonWebKey.KEY_TYPE;
import static com.alexander.scratchpad.jwt.jwks.model.JsonWebKey.MODULUS;
import static com.alexander.scratchpad.jwt.jwks.model.JsonWebKey.PUBLIC_EXPONENT;
import static com.alexander.scratchpad.jwt.jwks.model.JsonWebKey.USE;
import static org.assertj.core.api.Assertions.assertThat;

public class JsonWebKeyTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static String expected = "\"{\"alg\":\"RS256\",\"e\":null,\"kid\":\"1\",\"kty\":\"RSA\",\"n\":null,\"use\":null}\"";

    @Test
    void testToJson() throws IOException {
        JsonWebKey webKey = JsonWebKey.builder()
                .keyType(KeyType.RSA.toString())
                .alg(JwtAlg.RS256.toString())
                .kid("1")
                .use("sig")//enc
                .exponent("1234")
                .modulus("5678")
                .build();

        JsonNode output = mapper.readValue(mapper.writeValueAsString(webKey), JsonNode.class);
        assertThat(output.get(ALGORITHM).textValue()).isEqualTo("RS256");
        assertThat(output.get(KEY_ID).textValue()).isEqualTo("1");
        assertThat(output.get(KEY_TYPE).textValue()).isEqualTo("RSA");
        assertThat(output.get(PUBLIC_EXPONENT).textValue()).isEqualTo("1234");
        assertThat(output.get(MODULUS).textValue()).isEqualTo("5678");
        assertThat(output.get(USE).textValue()).isEqualTo("sig");

    }
}
