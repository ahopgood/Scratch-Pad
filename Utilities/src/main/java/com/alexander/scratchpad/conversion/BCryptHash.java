package com.alexander.scratchpad.conversion;

import com.alexander.scratchpad.crypto.Hashes;

import java.util.*;

/**
 * Utility for breaking down and verifying BCrypt hashes.
 * Header/Id makes use of linux <a href="https://en.wikipedia.org/wiki/Passwd#Shadow_file">shadow file</a> conventions.
 * Structure breakdown takes its lead from <a href="">wikipedia</a>, sigh.
 * Use <a href="http://passlib.readthedocs.io/en/stable/lib/passlib.hash.html#active-unix-hashes">passlib hash page</a> to locate header / id types and hash composition
 */
public class BCryptHash {

    public static String blowfish_gen1 = Hashes.blowfish_gen1;
    public static String blowfish_gen2 = Hashes.blowfish_gen2;
    public static String blowfish_gen3 = Hashes.blowfish_gen3;
    public static String blowfish_gen3_crypt = Hashes.blowfish_gen3_crypt; //crypt used by linux

    private int saltBitLength = 128;
    private int saltBase64Length = 22;
    private int hashBitLength = 184;
    private int hashBase64Length = 31;
    public static final int maximumCost = 31;

    private String header;
    private int cost; //number of key expansion rounds as a power of 2, e.g. 10 is 2^10
    private String salt;
    private String hash;

    public BCryptHash(String potentialBcryptHashString){
        header = extractPrefixHeader(potentialBcryptHashString);
        cost = extractCost(potentialBcryptHashString);
        salt = extractSalt(potentialBcryptHashString);
        hash = extractHash(potentialBcryptHashString);
    }

    public boolean isBcryptHash(){
        return false;
    }

    public String extractPrefixHeader(String potentialBcryptHashString){
        if (potentialBcryptHashString.contains(blowfish_gen1)){
            return blowfish_gen1;
        } else if (potentialBcryptHashString.contains(blowfish_gen2)) {
            return blowfish_gen2;
        } else if (potentialBcryptHashString.contains(blowfish_gen3)){
            return blowfish_gen3;
        } else if (potentialBcryptHashString.contains(blowfish_gen3_crypt)){
            return blowfish_gen3_crypt;
        } else {
            //Exception or error string?
            return "";
        }
    }

    public int extractCost(String potentialBcryptHashString){
        //between the second and third $ will be the cost/rounds
        String[] contents = splitOnDollars(potentialBcryptHashString);
        if (contents.length == expectedDollarSplits){
            String costString = contents[2];
            int cost = Integer.parseInt(costString);
            if (cost < 0 || cost > maximumCost){
                return -1;
            }
            return cost;
            //throws NumberFormatException, catch or throw?
        } else {
            //Exception or error value?
            return -1;
        }
    }

    public String extractSalt(String potentialBcryptHashString){
        //22 char salt in the regex range [./A-Za-z0-9]
        String[] contents = splitOnDollars(potentialBcryptHashString);
        if (contents.length == expectedDollarSplits){
            String trailingSaltAndHashString = contents[3];
            if (trailingSaltAndHashString.length() > saltBase64Length
                    && trailingSaltAndHashString.length() == (saltBase64Length + hashBase64Length)){
                String salt = trailingSaltAndHashString.substring(0, saltBase64Length);
                java.util.Base64.getDecoder().decode(salt);
                return salt;
            } else {
                //Exception or error value?
                return "";
            }
        } else {
            //Exception or error value?
            return "";
        }
    }

    public String extractHash(String potentialBcryptHashString){
        String[] contents = splitOnDollars(potentialBcryptHashString);
        if (contents.length == expectedDollarSplits){
            String trailingSaltAndHashString = contents[3];
            if (trailingSaltAndHashString.length() > saltBase64Length){
                if (trailingSaltAndHashString.length() == saltBase64Length + hashBase64Length){
                    String hash = trailingSaltAndHashString.substring(saltBase64Length, trailingSaltAndHashString.length());
                    java.util.Base64.getDecoder().decode(hash);
                    return hash;
                } else {
                    //Exception or error value?
                    return "";
                }
            } else {
                //Exception or error value?
                return "";
            }
        } else {
            //Exception or error value?
            return "";
        }

    }

    private int expectedDollarSplits = 4;

    private String[] splitOnDollars(String potentialBcryptHashString){
        return potentialBcryptHashString.split("\\$");
    }
}
