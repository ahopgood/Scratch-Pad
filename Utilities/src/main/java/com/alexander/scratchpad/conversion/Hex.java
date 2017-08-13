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

	public static Integer hexToInt(String hexString){
        return Integer.parseInt(hexString, 16);
    }

    public static void main(String[] args){
        String hex = "a30070e021838a3b22d69ca853f7092b8102f26b";
        Hex hexUtil = new Hex();
        Integer value = hexUtil.hexToInt(hex);
        System.out.println(value);
    }
}
