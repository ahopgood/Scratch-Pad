package com.alexander.scratchpad.conversion;

public class Base36 {
	
	static int radix = 36;
	
	public static String getBase36String(int integer){
		return Integer.toString(integer, radix).toUpperCase();
	}
	
	public static int getInteger(String base36String){
		return Integer.parseInt(base36String, radix);
	}

	public static String getBase36String(long longInt){
		return Long.toString(longInt, radix).toUpperCase();
	}

	public static long getLong(String base36String){
		return Long.parseLong(base36String, radix);
	}
	
}
