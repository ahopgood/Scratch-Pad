package com.alexander.scratchpad.conversion;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

/**
 * Class representing a basic http auth token.
 * @author Alexander
 *
 */
public class BasicHttpAuthToken {

	private String username = "";
	private String password = "";
	
	public BasicHttpAuthToken(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public BasicHttpAuthToken(String base64Token){
		if (base64Token != null){
			String stringToken = StringUtils.newStringUtf8(Base64.decodeBase64(base64Token));
			String[] tokens = stringToken.split(":");
			if (tokens.length > 0){
				username = tokens[0];
			}
			if (tokens.length > 1){
				password = tokens[1];
			}
		}
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getBasicHttpAuthToken(){
		String token = "";
		if (username != null && !username.isEmpty()){
			token += username;
		}
		token += ":";
		if (password != null && !password.isEmpty()){
			token += password;
		}
		token = StringUtils.newStringUtf8(Base64.encodeBase64(token.getBytes()));
		return token;
	}
}
