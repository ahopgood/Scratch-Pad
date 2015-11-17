package com.alexander.scratchpad.conversion;

public class Hex {

	public static byte[] hexToByteArray(String hexString){
		Long longVal = Long.parseLong(hexString, 16);
		System.out.println(Long.toHexString(longVal));
		
		return printArray(hexString.getBytes());
	}
	
	protected static byte[] printArray(byte[] array){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < array.length; i++){
			builder.append(array[i]);
			if (i < array.length-1){
				builder.append(",");
			}
		}
		System.out.println(builder.toString());
		return array;
	}
}
