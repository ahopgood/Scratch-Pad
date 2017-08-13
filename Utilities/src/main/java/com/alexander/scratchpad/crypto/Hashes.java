package com.alexander.scratchpad.crypto;

/**
 * Created by Alexander on 13/08/2017.
 */
public class Hashes {

    public static String blowfish_gen1 = "$2$";
    public static String blowfish_gen2 = "$2a$";
    public static String blowfish_gen3 = "$2b$";
    public static String blowfish_gen3_crypt = "$2y$"; //crypt used by linux
    private static String md5 = "$1$";
    private static String sha256 = "$5$";
    private static String sha512 = "$6$";
}
