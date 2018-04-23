package com.alexander.scratchpad.crypto;

/**
 * Created by Alexander on 13/08/2017.
 */
public class Hashes {

    public static String blowfish_gen1 = "$2$";
    public static String blowfish_gen2 = "$2a$";
    public static String blowfish_gen3 = "$2b$";
    public static String blowfish_gen3_crypt = "$2y$"; //crypt used by linux
    public static String md5 = "$1$";
    public static String sha256 = "$5$";
    public static String sha512 = "$6$";
    public static String jenkins_bcrypt = "jbcrypt:"+blowfish_gen2; //used by jenkins for password encryption
}
