package com.alexander.scratchpad.conversion;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Base36Test {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private String NEGATIVE 		= "-";
	private String oneString 		= "1";
	private String tenString		= "A";
	private String thirtyFiveString	= "Z";
	private String lowerCaseThirtyFiveString 	="z";
	private String nonAlphaNumericString 		= "[];';";
	
	//Int values
	private int oneInt					= 1;
	private int tenInt					= 10;
	private int thirtyFiveInt			= 35;
	private String minIntString			= NEGATIVE+"ZIK0ZK";
	private String maxIntString			= "ZIK0ZJ";
	private String lessThanIntMin		= "";
	private String greaterThanIntMax	= "";
	
	//Long values
	private long oneLong				= 1L;
	private long tenLong				= 10L;
	private long thirtyFiveLong			= 35L;
	private String minLongString		= NEGATIVE+"1Y2P0IJ32E8E8";
	private String maxLongString		= "1Y2P0IJ32E8E7";
	private String lessThanLongMin		= NEGATIVE+"1Y2P0IJ32E8E9";
	private String greaterThanLongMax 	= "1Y2P0IJ32E8E8";

	
	//Test GetInteger
	@Test public void 
	testGetInteger_givenOne_thenReturnOne() {
		assertEquals(oneInt, Base36.getInteger(oneString));
	}

	@Test public void 
	testGetInteger_givenA_thenReturnTen() {
		assertEquals(tenInt, Base36.getInteger(tenString));
	}

	@Test public void 
	testGetInteger_givenZ_thenReturnThirtySix() {
		assertEquals(thirtyFiveInt, Base36.getInteger(thirtyFiveString));
	}

	@Test public void 
	testGetInteger_givenLowerCaseString_thenThrowException() {
		assertEquals(thirtyFiveInt, Base36.getInteger(lowerCaseThirtyFiveString));
	}
	
	@Test (expected = NumberFormatException.class) public void 
	testGetInteger_givenNonAlphaNumericString_thenThrowException() {
		Base36.getInteger(this.nonAlphaNumericString);
	}
	
	@Test (expected = NumberFormatException.class) public void 
	testGetInteger_givenNullString_thenThrowException() {
		Base36.getInteger(null);
	}

	@Test (expected = NumberFormatException.class) public void 
	testGetInteger_givenEmptyStringString_thenThrowException() {
		Base36.getInteger("");
	}
	
	@Test (expected = NumberFormatException.class) public void 
	testGetInteger_givenWhitespaceString_thenThrowException() {
		Base36.getInteger("    ");
	}

	@Test (expected = NumberFormatException.class) public void 
	testGetInteger_givenStringValueGreaterThanIntMax_thenThrowException() {
		Base36.getLong(this.greaterThanIntMax);
	}
	
	@Test (expected = NumberFormatException.class) public void 
	testGetInteger_givenStringValueLessThanIntMin_thenThrowException() {
		Base36.getLong(this.lessThanIntMin);
	}
	
	//Test GetBase36String
	@Test public void 
	testGetBase36_givenOne_thenReturnOne() {
		assertEquals(true, oneString.equalsIgnoreCase(Base36.getBase36String(oneInt)));
	}

	@Test public void 
	testGetBase36_givenTen_thenReturnA() {
		assertEquals(tenString, Base36.getBase36String(tenInt));
	}

	@Test public void 
	testGetBase36_givenThirtySix_thenReturnZ() {
		assertEquals(thirtyFiveString, Base36.getBase36String(thirtyFiveInt));
	}

	@Test public void 
	testGetBase36_givenNegativeNumber_thenReturnZ() {
		assertEquals(NEGATIVE+thirtyFiveString, Base36.getBase36String(-35));
	}

	@Test public void 
	testGetBase36_givenMinInt_thenReturnMinIntString() {
		assertEquals(minIntString, Base36.getBase36String(Integer.MIN_VALUE));
	}

	@Test public void 
	testGetBase36_givenMinInt_thenRollToMaxIntString() {
		assertEquals(maxIntString, Base36.getBase36String(Integer.MIN_VALUE-1));
	}
	
	@Test public void 
	testGetBase36_givenMaxInt_thenReturnMaxIntString() {
		assertEquals(maxIntString, Base36.getBase36String(Integer.MAX_VALUE));
	}

	@Test public void 
	testGetBase36_givenMaxInt_thenRollMinIntString() {
		assertEquals(minIntString, Base36.getBase36String(Integer.MAX_VALUE+1));
	}

	//Test GetLong
	@Test public void 
	testGetLong_givenOne_thenReturnOne() {
		assertEquals(oneLong, Base36.getLong(oneString));
	}

	@Test public void 
	testGetLong_givenA_thenReturnTen() {
		assertEquals(tenLong, Base36.getLong(tenString));
	}

	@Test public void 
	testGetLong_givenZ_thenReturnThirtySix() {
		assertEquals(thirtyFiveLong, Base36.getLong(thirtyFiveString));
	}

	@Test public void 
	testGetLong_givenLowerCaseString_thenThrowException() {
		assertEquals(thirtyFiveLong, Base36.getLong(lowerCaseThirtyFiveString));
	}
	
	@Test (expected = NumberFormatException.class) public void 
	testGetLong_givenNonAlphaNumericString_thenThrowException() {
		Base36.getLong(this.nonAlphaNumericString);
	}
	
	@Test (expected = NumberFormatException.class) public void 
	testGetLong_givenNullString_thenThrowException() {
		Base36.getLong(null);
	}

	@Test (expected = NumberFormatException.class) public void 
	testGetLong_givenEmptyStringString_thenThrowException() {
		Base36.getLong("");
	}
	
	@Test (expected = NumberFormatException.class) public void 
	testGetLong_givenWhitespaceString_thenThrowException() {
		Base36.getLong("    ");
	}

	@Test (expected = NumberFormatException.class) public void 
	testGetLong_givenStringValueGreaterThanLongMax_thenThrowException() {
		Base36.getLong(this.greaterThanLongMax);
	}
	
	@Test (expected = NumberFormatException.class) public void 
	testGetLong_givenStringValueLessThanLongMin_thenThrowException() {
		Base36.getLong(this.lessThanLongMin);
	}
	
	//Test GetBase36String Long
	@Test public void 
	testGetBase36_givenOneLong_thenReturnOne() {
		assertEquals(true, oneString.equalsIgnoreCase(Base36.getBase36String(oneLong)));
	}

	@Test public void 
	testGetBase36_givenTenLong_thenReturnA() {
		assertEquals(tenString, Base36.getBase36String(tenLong));
	}

	@Test public void 
	testGetBase36_givenThirtySixLong_thenReturnZ() {
		assertEquals(thirtyFiveString, Base36.getBase36String(thirtyFiveLong));
	}

	@Test public void 
	testGetBase36_givenNegativeLong_thenReturnZ() {
		assertEquals(NEGATIVE+thirtyFiveString, Base36.getBase36String(-35L));
	}

	@Test public void 
	testGetBase36_givenMinLong_thenReturnMinLongString() {
		assertEquals(minLongString, Base36.getBase36String(Long.MIN_VALUE));
	}

	@Test public void 
	testGetBase36_givenLessThanMinLong_thenReturnRollToMaxLong() {
		assertEquals(maxLongString, Base36.getBase36String(Long.MIN_VALUE-1));
	}

	@Test public void 
	testGetBase36_givenMaxLong_thenReturnMaxLongString() {
		assertEquals(maxLongString, Base36.getBase36String(Long.MAX_VALUE));
	}

	@Test public void 
	testGetBase36_givenGreaterThanMaxLong_thenReturnRollToMinLong() {
		assertEquals(minLongString, Base36.getBase36String(Long.MAX_VALUE+1));
	}

}

















