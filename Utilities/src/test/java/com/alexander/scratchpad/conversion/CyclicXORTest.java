package com.alexander.scratchpad.conversion;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CyclicXORTest {

	byte[] obfuscatedSeedBytes = new byte[] { 0x41, 0x43, 0x33, 0x31, 0x37, 0x31, 0x33, 0x31, 0x3F, 0x31, 0x33, 0x48, 0x4D, 0x4F, 0x3F, 0x3D } ;
	byte[] unobfuscatedSeedBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F };

	long eTicketNumber 		= 0xAB123456789CL;
	String eTicketNumberStr = "AB123456789C";
	
	CyclicXOR xor = new CyclicXOR();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test public void 
	testWatermarkingXORWithLongIncorrect() {
		byte[] result = xor.xorSeed(unobfuscatedSeedBytes, eTicketNumber);
		printArray(obfuscatedSeedBytes);
		printArray(result);
		assertArrayEquals(obfuscatedSeedBytes, result);
	}

	@Test public void 
	testWatermarkingXORWithLongCorrect() {
		byte[] result = xor.xorSeedCorrect(unobfuscatedSeedBytes, eTicketNumber);
		printArray(obfuscatedSeedBytes);
		printArray(result);
		assertArrayEquals(obfuscatedSeedBytes, result);
	}

	
	@Test public void 
	testWatermarkingXORWithString() {
		byte[] result = xor.xorSeed(unobfuscatedSeedBytes, eTicketNumberStr);
		printArray(obfuscatedSeedBytes);
		printArray(result);
		assertArrayEquals(obfuscatedSeedBytes, result);
	}

	@Test public void
	testDefaultVisValProviderXORValue(){
		assertArrayEquals(obfuscatedSeedBytes, xor.getUnobfuscatedSeed(unobfuscatedSeedBytes, xor.getVisualValidationXORValue(eTicketNumberStr)));
	}

	@Test public void
	testUKServerObfuscateSeed(){
		byte[] result = xor.obfuscatedSeed(eTicketNumberStr, unobfuscatedSeedBytes);
//				xor.xorSeed(unobfuscatedSeedBytes, eTicketNumber);
		printArray(obfuscatedSeedBytes);
		printArray(result);
		assertArrayEquals(obfuscatedSeedBytes, result);
	}
	
	protected void printArray(byte[] array){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < array.length; i++){
			byte b = array[i];
			builder.append(b);
			if (i != array.length-1){
				builder.append(",");
			}
		}
		System.out.println(builder.toString());
	}
}
