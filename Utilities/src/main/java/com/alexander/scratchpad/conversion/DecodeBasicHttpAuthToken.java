package com.alexander.scratchpad.conversion;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public class DecodeBasicHttpAuthToken {
	
	public static void main(String[] args){
		String authString = "user";
		System.out.println(StringUtils.newStringUtf8(Base64.encodeBase64(authString.getBytes())));
		
		
		BasicHttpAuthToken token = new BasicHttpAuthToken("YWJlbGxpb2dhOls0YFtZNTd2");
		
		System.out.println(token.getUsername());
		System.out.println(token.getPassword());
	}
	
}