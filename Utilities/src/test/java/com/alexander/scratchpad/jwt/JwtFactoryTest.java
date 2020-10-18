package com.alexander.scratchpad.jwt;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JwtFactoryTest {

    private String secret = "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy";
    private String service = "myservice";

    private String serviceToken = "eyJhbGciOiJIUzUxMiJ9.eyJzZXJ2aWNlIjoibXlzZXJ2aWNlIn0.oBCeEwL2kP3Gud-FpJe8Z4rbCCebTtrjfNSuKOxncDXzDdM8QWiQW-AttM4nVvvUFQPbWUd55CFMOpUqlj5scw";

    private String subject = "123abc789xyz";
    private String expiringToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjNhYmM3ODl4eXoiLCJleHAiOjE1MDIxOTM2MDAwMDB9.Zv9i0Q78mJRZpM4csZzhYQg2nIhFlB3rKYqdZnscQQ1ZrkU0eU54ykHrRuKpPBIQaROQ4jwy6ZU7oobLz_MZtw";
    private String expiringTokenSecret = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    private Object token = null;

    @Test
    void testGenerateJwtString_fromNullServiceToken() {
        assertThrows(JwtEncodingException.class, () -> JwtFactory.generateJwtString(null, secret));
    }

    @Test
    void testGenerateJwtString_givenNullSecret() {
        assertThrows(JwtEncodingException.class, () -> JwtFactory.generateJwtString(token, null));
    }

    @Test
    void testGenerateJwtString_givenServiceToken() throws JwtEncodingException {
        String encodedToken = JwtFactory.generateJwtString(new ServiceToken(service), secret);
        System.out.println(encodedToken);
        assertEquals(serviceToken,encodedToken);
    }

    @Test
    void testGenerateJwtString_fromExpiringToken() throws JwtEncodingException {
        ExpiringToken expiringToken = new ExpiringToken();
        expiringToken.setSub(subject);
        expiringToken.setExp(
                LocalDateTime.
                        of(2017,8,8,12,0).toInstant(ZoneOffset.UTC).toEpochMilli());

        String encodedToken = JwtFactory.generateJwtString(expiringToken, expiringTokenSecret);
        System.out.println(encodedToken);
        assertEquals(this.expiringToken,encodedToken);
    }
}
