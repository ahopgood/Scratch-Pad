package com.alexander.scratchpad.conversion;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class HexTest {

    // UTF-8 Bytes
	//Hex String vs Byte values
	private final String zero 	    = "0";
	private final byte[] zeroArray    = new byte[]{48};
	private final String nine	        = "9";
	private final byte[] nineArray	= new byte[]{57};
	private final String ten		    = "A";
	private final byte[] tenArray	    = new byte[]{65};
	private final String fifteen	    = "F";
	private final byte[] fifteenArray	= new byte[]{70};
	private final String sixteen	    = "10";
	private final byte[] sixteenArray	= new byte[]{49,48};
	private final String oneHundredTwentySeven 		= "80";
	private final byte[] oneHunredAndTwentySevenArray	= new byte[]{56,48};
	
	private final String nonHexString			= "GGG";
	private final String emptyHexString 		= "";
	private final String whitespaceHexString	= "   ";

	@Test
    void testHexToByteArray_givenHexZero() {
		assertArrayEquals(zeroArray, Hex.hexToByteArray(zero));
	}
	
	@Test
    void testHexToByteArray_givenHexNine(){
		assertArrayEquals(nineArray, Hex.hexToByteArray(nine));
	}

	@Test
    void testHexToByteArray_givenHexTen(){
		assertArrayEquals(tenArray, Hex.hexToByteArray(ten));
	}

	@Test
    void testHexToByteArray_givenHexFifteen(){
		assertArrayEquals(fifteenArray, Hex.hexToByteArray(fifteen));
	}
	
	@Test
    void testHexToByteArray_givenHexSixteen(){
		assertArrayEquals(sixteenArray, Hex.hexToByteArray(sixteen));
	}
	
	@Test
    void testHexToByteArray_givenHexGreaterThan127(){
		assertArrayEquals(this.oneHunredAndTwentySevenArray, Hex.hexToByteArray(this.oneHundredTwentySeven));
	}
	
}
