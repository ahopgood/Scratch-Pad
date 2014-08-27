package com.alexander.scratchpad.conversion;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public class BasicHttpAuthToken {

	private String username;
	private String password;
	
	public BasicHttpAuthToken(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public BasicHttpAuthToken(String base64Token){
		//null string
		//empty string
		//whitepace string
		//non base64 string
		//base64 string with multiple ":" values
		if (base64Token != null){
			String stringToken = StringUtils.newStringUtf8(Base64.decodeBase64(base64Token));
			int separator = stringToken.indexOf(":");
			password = stringToken.substring(separator+1);
			username = stringToken.substring(0, separator);
		}
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
