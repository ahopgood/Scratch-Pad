package com.alexander.scratchpad.conversion;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HexTest {

    // UTF-8 Bytes
	//Hex String vs Byte values
	private String zero 	    = "0";
	private byte[] zeroArray    = new byte[]{48};
	private String nine	        = "9";
	private byte[] nineArray	= new byte[]{57};
	private String ten		    = "A";
	private byte[] tenArray	    = new byte[]{65};
	private String fifteen	    = "F";
	private byte[] fifteenArray	= new byte[]{70};
	private String sixteen	    = "10";
	private byte[] sixteenArray	= new byte[]{49,48};
	private String oneHundredTwentySeven 		= "80";
	private byte[] oneHunredAndTwentySevenArray	= new byte[]{56,48};
	
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
