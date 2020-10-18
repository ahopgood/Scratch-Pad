package com.alexander.scratchpad.conversion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicHttpAuthTokenTest {

	private final String sampleBase64Token = "dXNlcjpwYXNzd29yZA==";
	private final String sampleUsername = "user";
	private final String samplePassword	= "password";
	private final String sampleBase64Username = "dXNlcjo=";
	private final String sampleBase64Password	= "OnBhc3N3b3Jk";
	
	private final String usernameWhitespacePassword	= "dXNlcjogICAg";
	private final String whitespaceUsernamePassword	= "ICAgIDpwYXNzd29yZA==";
	private final String emptyToken = "";
	private final String emptyBase64Token = "Og==";

	private final String whitespaceToken = "    ";
	private final String nonBase64Token = ";'@;./[]#~'=_+&$(^&*";
	private final String tokenWithMultipleSemiColons = "dXNlcjpwYXNzd29yZDpzZWNvbmQ6dGhpcmQ=";
	private final String tokenWithoutSemiColon = "dXNlcg==";

	@Test
    void testConstructor_givenNullToken() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(null);
		assertEquals(emptyToken, 	token.getUsername());
		assertEquals(emptyToken, 	token.getPassword());
	}
	
	@Test
    void testConstructor_givenEmptyToken() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(emptyToken);
		assertEquals(emptyToken, 	token.getUsername());
		assertEquals(emptyToken,	token.getPassword());
	}

	@Test
    void testConstructor_givenWhitespaceToken() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(whitespaceToken);
		assertEquals(emptyToken, 	token.getUsername());
		assertEquals(emptyToken, 	token.getPassword());
	}

	@Test
    void testConstructor_givenNonBase64Token() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(nonBase64Token);
		assertEquals(emptyToken, 	token.getUsername());
		assertEquals(emptyToken, 	token.getPassword());
	}

	@Test
    void testConstructor_givenTokenWithMultipleSemiColons() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(tokenWithMultipleSemiColons);
		assertEquals(sampleUsername, token.getUsername());
		assertEquals(samplePassword, token.getPassword());

	}

	@Test
    void testConstructor_givenTokenWithoutSemiColons() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(tokenWithoutSemiColon);
		assertEquals(sampleUsername, 	token.getUsername());
		assertEquals(emptyToken, 		token.getPassword());
	}

	@Test
    void testConstructor_givenToken() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(sampleBase64Token);
		assertEquals(sampleUsername, token.getUsername());
		assertEquals(samplePassword, token.getPassword());
	}
	
	//Test GetBasicHttpAuthToken with token constructor setup
	@Test
    void testGetBasicHttpAuthToken_givenNullToken() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(null);
		assertEquals(emptyBase64Token, 	token.getBasicHttpAuthToken());
	}
	
	@Test
    void testGetBasicHttpAuthToken_givenEmptyToken() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(emptyToken);
		assertEquals(emptyBase64Token, 	token.getBasicHttpAuthToken());
	}

	@Test
    void testGetBasicHttpAuthToken_givenWhitespaceToken() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(whitespaceToken);
		assertEquals(emptyBase64Token, 	token.getBasicHttpAuthToken());
	}

	@Test
    void testGetBasicHttpAuthToken_givenNonBase64Token() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(nonBase64Token);
		assertEquals(emptyBase64Token, 	token.getBasicHttpAuthToken());
	}

	@Test
    void testGetBasicHttpAuthToken_givenTokenWithMultipleSemiColons() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(tokenWithMultipleSemiColons);
		assertEquals("dXNlcjpwYXNzd29yZA==", 	token.getBasicHttpAuthToken());
	}

	@Test
    void testGetBasicHttpAuthToken_givenTokenWithoutSemiColons() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(tokenWithoutSemiColon);
		assertEquals("dXNlcjo=", 	token.getBasicHttpAuthToken());
	}

	@Test
    void testGetBasicHttpAuthToken_givenTokenConstructor(){
		BasicHttpAuthToken token = new BasicHttpAuthToken(sampleBase64Token);
		assertEquals(sampleBase64Token, token.getBasicHttpAuthToken());
	}
	
	//Test GetBasicHttpAuthToken with username and password constructor
	@Test
    void testGetBasicHttpAuthToken_givenUserPasswordConstructor(){
		BasicHttpAuthToken token = new BasicHttpAuthToken(sampleUsername, samplePassword);
		assertEquals(sampleBase64Token, token.getBasicHttpAuthToken());
	}
	
	@Test
    void testGetBasicHttpAuthToken_givenNullUsername() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(null, samplePassword);
		assertEquals(sampleBase64Password, 	token.getBasicHttpAuthToken());
	}
	
	@Test
    void testGetBasicHttpAuthToken_givenNullPassword() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(sampleUsername, null);
		assertEquals(sampleBase64Username, 	token.getBasicHttpAuthToken());
	}
	
	@Test
    void testGetBasicHttpAuthToken_givenNullUsernameAndPassword() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(null, null);
		assertEquals(emptyBase64Token, 	token.getBasicHttpAuthToken());
		
	}
	
	@Test
    void testGetBasicHttpAuthToken_givenEmptyPassword() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(sampleUsername, emptyToken);
		assertEquals(sampleBase64Username, 	token.getBasicHttpAuthToken());
	}
	
	@Test
    void testGetBasicHttpAuthToken_givenEmptyUsername() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(emptyToken, samplePassword);
		assertEquals(sampleBase64Password, 	token.getBasicHttpAuthToken());
	}

	@Test
    void testGetBasicHttpAuthToken_givenWhitespacePassword() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(sampleUsername, whitespaceToken);
		assertEquals(usernameWhitespacePassword, 	token.getBasicHttpAuthToken());
	}
	
	@Test
    void testGetBasicHttpAuthToken_givenWhitespaceUsername() {
		BasicHttpAuthToken token = new BasicHttpAuthToken(whitespaceToken, samplePassword);
		assertEquals(whitespaceUsernamePassword, 	token.getBasicHttpAuthToken());
	}
}
