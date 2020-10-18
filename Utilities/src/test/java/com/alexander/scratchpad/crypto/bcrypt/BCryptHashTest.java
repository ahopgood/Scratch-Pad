package com.alexander.scratchpad.crypto.bcrypt;

import com.alexander.scratchpad.conversion.BCryptHash;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BCryptHashTest {

    String saltString   = "N9qo8uLOickgx2ZMRZoMye";
    String hashString   = "IjZAgcfl7p92ldGxad68LJZdL17lhWy";
    String cost         = "10$";

    String gen1         = BCryptHash.blowfish_gen1+cost+saltString+hashString;
    String gen2         = BCryptHash.blowfish_gen2+cost+saltString+hashString;
    String gen3         = BCryptHash.blowfish_gen3+cost+saltString+hashString;
    String gen3_crypt   = BCryptHash.blowfish_gen3_crypt+cost+saltString+hashString;

    @Test
    void testGen1BlowfishHash(){
        BCryptHash hash = new BCryptHash(gen1);
        assertEquals(BCryptHash.blowfish_gen1, hash.extractPrefixHeader(gen1));
    }


    @Test
    void testGen2BlowfishHash(){
        BCryptHash hash = new BCryptHash(gen1);
        assertEquals(BCryptHash.blowfish_gen2, hash.extractPrefixHeader(gen2));
    }


    @Test
    void testGen3BlowfishHash(){
        BCryptHash hash = new BCryptHash(gen3);
        assertEquals(BCryptHash.blowfish_gen3, hash.extractPrefixHeader(gen3));
    }


    @Test
    void testGen3_cryptBlowfishHash(){
        BCryptHash hash = new BCryptHash(gen3_crypt);
        assertEquals(BCryptHash.blowfish_gen3_crypt, hash.extractPrefixHeader(gen3_crypt));
    }

    String negativeCost     = BCryptHash.blowfish_gen3+"-2$"+saltString+hashString;
    String zeroCost         = BCryptHash.blowfish_gen3+"00$"+saltString+hashString;
    String tenCost          = BCryptHash.blowfish_gen3+"10$"+saltString+hashString;
    String thirtyTwoCost    = BCryptHash.blowfish_gen3+"32$"+saltString+hashString;
    String malformedCost    = BCryptHash.blowfish_gen3+"aa$"+saltString+hashString;

    @Test
    void testAnyGen_costIsNegative_BlowfishHash(){
        BCryptHash hash = new BCryptHash(negativeCost);
        assertEquals(-1, hash.extractCost(negativeCost));
    }

    @Test
    void testAnyGen_costIsZero_BlowfishHash(){
        BCryptHash hash = new BCryptHash(zeroCost);
        assertEquals(0, hash.extractCost(zeroCost));
    }

    @Test
    void testAnyGen_costIsTen_BlowfishHash(){
        BCryptHash hash = new BCryptHash(tenCost);
        assertEquals(10, hash.extractCost(tenCost));
    }

    @Test
    void testAnyGen_costIsGreaterThanLimit_BlowfishHash(){
        BCryptHash hash = new BCryptHash(thirtyTwoCost);
        assertEquals(-1, hash.extractCost(thirtyTwoCost));
    }

    @Test
    void testAnyGen_costIsMalformed_BlowfishHash(){
        assertThrows(NumberFormatException.class, () -> new BCryptHash(malformedCost));
    }

    String emptySalt        = BCryptHash.blowfish_gen3+cost+""+hashString;
    String saltPresent      = BCryptHash.blowfish_gen3+cost+saltString+hashString;
    String malformedSalt    = BCryptHash.blowfish_gen3+cost+"N9qo8uLOickgx2ZMRZΩΩΩΩ"+hashString;
    String shortSalt        = BCryptHash.blowfish_gen3+cost+"N9qo8uLOickgx2ZMRZo"+hashString;
    String longSalt         = BCryptHash.blowfish_gen3+cost+saltString+"yyy"+hashString;

    @Test
    void testAnyGen_withEmptySalt_BlowfishHash(){
        BCryptHash hash = new BCryptHash(emptySalt);
        assertEquals("", hash.extractSalt(emptySalt));
    }

    @Test
    void testAnyGen_withSaltPresent_BlowfishHash(){
        BCryptHash hash = new BCryptHash(saltPresent);
        assertEquals(saltString, hash.extractSalt(saltPresent));
    }

    @Test
    void testAnyGen_withMalformedSalt_BlowfishHash(){
        assertThrows(IllegalArgumentException.class, () -> new BCryptHash(malformedSalt));
    }

    @Test
    void testAnyGen_withShortSalt_BlowfishHash(){
        BCryptHash hash = new BCryptHash(shortSalt);
        assertEquals("", hash.extractSalt(shortSalt));
    }

    @Test
    void testAnyGen_withLongSalt_BlowfishHash(){
        BCryptHash hash = new BCryptHash(longSalt);
        assertEquals("", hash.extractSalt(longSalt));
    }

    String emptyHash        = BCryptHash.blowfish_gen3+cost+saltString+"";
    String hashPresent      = BCryptHash.blowfish_gen3+cost+saltString+hashString;
    String malformedHash    = BCryptHash.blowfish_gen3+cost+saltString+"IjZAgcfl7p92ldGxad68LJZdLΩΩΩΩΩΩ";
    String shortHash        = BCryptHash.blowfish_gen3+cost+saltString+"IjZAgcfl7p92ldGxad68LJZdL17lhW";
    String longHash         = BCryptHash.blowfish_gen3+cost+saltString+hashString+"yyy";

    @Test
    void testAnyGen_withEmptyHash_BlowfishHash(){
        BCryptHash hash = new BCryptHash(emptyHash);
        assertEquals("", hash.extractHash(emptyHash));
    }

    @Test
    void testAnyGen_withHashPresent_BlowfishHash(){
        BCryptHash hash = new BCryptHash(hashPresent);
        assertEquals(hashString, hash.extractHash(hashPresent));
    }

    @Test
    void testAnyGen_withMalformedHash_BlowfishHash(){
        assertThrows(IllegalArgumentException.class, () -> new BCryptHash(malformedHash));
    }

    @Test
    void testAnyGen_withShortHash_BlowfishHash(){
        BCryptHash hash = new BCryptHash(shortHash);
        assertEquals("", hash.extractHash(shortHash));
    }

    @Test
    void testAnyGen_withLongHash_BlowfishHash(){
        BCryptHash hash = new BCryptHash(longHash);
        assertEquals("", hash.extractHash(longHash));
    }
}
