package com.alexander.scratchpad.conversion;

public class CyclicXOR {

	byte[] obfuscatedSeedBytes = new byte[] { 0x41, 0x43, 0x33, 0x31, 0x37, 0x31, 0x33, 0x31, 0x3F, 0x31, 0x33, 0x48, 0x4D, 0x4F, 0x3F, 0x3D } ;
	byte[] unobfuscatedSeedBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F };

	long eTicketNumber = 0xAB123456789CL;

	
	//From Watermarking project
	//This doesn't work!
	protected byte[] xorSeed(byte[] seed, Long xorValue){
		byte[] result = new byte[seed.length];
		for (int i = 0; i < seed.length; i++){
			result[i] = (byte) (seed[i]^ (xorValue.longValue() >> 8 *(i&3)));
		}
		return result;
	}

	protected byte[] xorSeedCorrect(byte[] seed, Long xorValue){
		byte[] result = new byte[seed.length];
		String xorString	= Long.toHexString(xorValue).toUpperCase();
		System.out.println(xorString);
		byte[] xorBytes = 	xorString.getBytes();
		for (int i = 0; i < seed.length; i++){
			result[i] = (byte) (seed[i]^ xorBytes[i % xorBytes.length]);
		}
		return result;
	}

	
	protected byte[] xorSeed(byte[] seed, String xorValue){
		byte[] xorBytes = xorValue.getBytes();
		
		byte[] result = new byte[seed.length];
		for (int i = 0; i < seed.length; i++){
			result[i] = (byte) (seed[i] ^ xorBytes[i % xorBytes.length]);
		}
		return result;
	}

	//From default VisVal palette provider
	public byte[] getVisualValidationXORValue(String eTicketNumber) {
		/*
		 * Need to make the eTicketNumber byte array up to 128 bits (16 bytes).
		 * It's original length is 12 so we need to take the highest four bytes
		 * and concatenate them to the bottom four bytes of the 16 byte array.
		 */
		final int eTicketNumberLength = eTicketNumber.length();
		byte[] xorValue = new byte[16];
		byte[] eTicketNumberBytes = eTicketNumber.getBytes();
		System.arraycopy(eTicketNumberBytes, 0, xorValue, 0, eTicketNumberLength);
		if (eTicketNumberLength < 16)
			System.arraycopy(eTicketNumberBytes, 0, xorValue, eTicketNumberLength, 16 - eTicketNumberLength);

		return xorValue;
	}

	public final byte[] getUnobfuscatedSeed(byte[] obfuscatedSeed, byte[] xorValue) {
		if (obfuscatedSeed.length != xorValue.length)
			return null;

		byte[] unobfuscatedSeed = new byte[obfuscatedSeed.length];

		for (int i = 0; i < unobfuscatedSeed.length; i++) {
			unobfuscatedSeed[i] = (byte) (obfuscatedSeed[i] ^ xorValue[i]);
		}

		return unobfuscatedSeed;
	}

	
	//From UK server
	public byte[] obfuscatedSeed(String eTicketNumber, byte[] obfuscatedSeed) {		
		byte[] concatenatedETicketNumber = concatenateETicketNumberTo24Bytes(eTicketNumber);
		byte[] concatenated16ByteETicketNumber = convertConcatenatedETicketBytesTo16Bytes(concatenatedETicketNumber);
		return xor16ByteTicketNumberWithSeed(concatenated16ByteETicketNumber, obfuscatedSeed);
	}
	
	byte[] concatenateETicketNumberTo24Bytes(String eTicketNumber) {
		byte[] eTicketNumberBytes = eTicketNumber.getBytes();
		byte[] concatenatedTicketNumber = new byte[24];
		
		System.arraycopy(eTicketNumberBytes, 0, concatenatedTicketNumber, 0, eTicketNumberBytes.length);
		System.arraycopy(eTicketNumberBytes, 0, concatenatedTicketNumber,  eTicketNumberBytes.length, eTicketNumberBytes.length);
		return concatenatedTicketNumber;
	}

	byte[] convertConcatenatedETicketBytesTo16Bytes(byte[] concatenated24Bytes) {
		byte[] trimmedBytes = new byte[16];
		
		System.arraycopy(concatenated24Bytes, 0, trimmedBytes, 0, trimmedBytes.length);
		
		return trimmedBytes;
	}

	byte[] xor16ByteTicketNumberWithSeed(byte[] trimmed16Bytes, byte[] unobfuscatedSeed) {
		byte[] xored16Bytes = new byte[16];
		for (int i = 0; i < xored16Bytes.length; i++) {
			xored16Bytes[i] = (byte) (trimmed16Bytes[i] ^ unobfuscatedSeed[i]);
		}
		return xored16Bytes;
	}

}
