package com.alexander.scratchpad.crypto;

import javax.crypto.Cipher;

/**
 * Tests if unlimited Java Cryptography Extensions are enabled on this JVM by performing a simple RC5 length check.
 */
public class CryptoTest {
  public static void main(String args[]) throws Exception {
    boolean unlimited =
      Cipher.getMaxAllowedKeyLength("RC5") >= 256;
    System.out.println("Unlimited cryptography enabled: " + unlimited);
  }
}
