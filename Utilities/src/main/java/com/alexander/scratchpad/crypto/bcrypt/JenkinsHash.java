package com.alexander.scratchpad.crypto.bcrypt;

import org.mindrot.jbcrypt.BCrypt;

public class JenkinsHash {

    public static String jbcrypt = "jbcrypt:";
    private static int costFactor = 10;

    public static String generateHash(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(costFactor));
    }
    public static String generateHashWithPrefix(String password){
        return jbcrypt + generateHash(password);
    }

    public static void main(String[] args){
        System.out.println(JenkinsHash.generateHash(""));
    }
}
