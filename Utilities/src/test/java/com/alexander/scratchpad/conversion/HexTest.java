package com.alexander.scratchpad.conversion;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HexTest {

	//Hex String vs Byte values
	private String zero 	= "0";
	private byte[] zeroArray	= new byte[]{0};
	private String nine		= "9";
	private byte[] nineArray	= new byte[]{9};
	private String ten		= "A";
	private byte[] tenArray	= new byte[]{10};
	private String fifteen	= "F";
	private byte[] fifteenArray	= new byte[]{15};
	private String sixteen	= "10";
	private byte[] sixteenArray	= new byte[]{16};
	private String oneHundredTwentySeven 		= "80";
	private byte[] oneHunredAndTwentySevenArray	= new byte[]{127};
	
	private String nonHexString			= "GGG";
	private String emptyHexString 		= "";
	private String whitespaceHexString	= "   ";
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test public void 
	testHexToByteArray_givenHexZero() {
		assertArrayEquals(zeroArray, Hex.hexToByteArray(zero));
	}
	
	@Test public void
	testHexToByteArray_givenHexNine(){
		assertArrayEquals(nineArray, Hex.hexToByteArray(nine));
	}

	@Test public void
	testHexToByteArray_givenHexTen(){
		assertArrayEquals(tenArray, Hex.hexToByteArray(ten));
	}

	@Test public void
	testHexToByteArray_givenHexFifteen(){
		assertArrayEquals(fifteenArray, Hex.hexToByteArray(fifteen));
	}
	
	@Test public void
	testHexToByteArray_givenHexSixteen(){
		assertArrayEquals(sixteenArray, Hex.hexToByteArray(sixteen));
	}
	
	@Test public void
	testHexToByteArray_givenHexGreaterThan127(){
		assertArrayEquals(this.oneHunredAndTwentySevenArray, Hex.hexToByteArray(this.oneHundredTwentySeven));
	}
	
}
