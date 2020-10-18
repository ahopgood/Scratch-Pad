package com.alexander.scratchpad.jwt;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.Assert.*;

public class JwtFactoryTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private String secret = "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy";
    private String service = "myservice";

    private String serviceToken = "eyJhbGciOiJIUzUxMiJ9.eyJzZXJ2aWNlIjoibXlzZXJ2aWNlIn0.oBCeEwL2kP3Gud-FpJe8Z4rbCCebTtrjfNSuKOxncDXzDdM8QWiQW-AttM4nVvvUFQPbWUd55CFMOpUqlj5scw";

    private String subject = "123abc789xyz";
    private String expiringToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjNhYmM3ODl4eXoiLCJleHAiOjE1MDIxOTM2MDAwMDB9.Zv9i0Q78mJRZpM4csZzhYQg2nIhFlB3rKYqdZnscQQ1ZrkU0eU54ykHrRuKpPBIQaROQ4jwy6ZU7oobLz_MZtw";
    private String expiringTokenSecret = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    private Object token = null;

    @Test (expected = JwtEncodingException.class)
    public void testGenerateJwtString_fromNullServiceToken() throws JwtEncodingException {
        JwtFactory.generateJwtString(null, secret);
    }

    @Test (expected = JwtEncodingException.class)
    public void testGenerateJwtString_givenNullSecret() throws JwtEncodingException {
        JwtFactory.generateJwtString(token, null);
    }

    @Test
    public void testGenerateJwtString_givenServiceToken() throws JwtEncodingException {
        String encodedToken = JwtFactory.generateJwtString(new ServiceToken(service), secret);
        System.out.println(encodedToken);
        assertEquals(serviceToken,encodedToken);
    }

    @Test
    public void testGenerateJwtString_fromExpiringToken() throws JwtEncodingException {
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
