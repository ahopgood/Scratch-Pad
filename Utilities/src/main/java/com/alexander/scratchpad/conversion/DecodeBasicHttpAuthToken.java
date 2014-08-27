package com.alexander.scratchpad.conversion;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public class DecodeBasicHttpAuthToken {

	public static String getUsername(String base64){
		return StringUtils.newStringUtf8(Base64.decodeBase64(base64));
	}
	
	public static void main(String[] args){
		BasicHttpAuthToken token = new BasicHttpAuthToken("YWJlbGxpb2dhOls0YFtZNTd2");
		
		System.out.println(token.getUsername());
		System.out.println(token.getPassword());
	}
	
}