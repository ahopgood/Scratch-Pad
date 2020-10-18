package com.alexander.scratchpad.crypto.bcrypt;

import com.alexander.scratchpad.crypto.Hashes;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JenkinsHashTest {

    private String password = "admin";

    @Test
    void testGenerateHash(){
        String hash = JenkinsHash.generateHash(password);
        assertTrue(hash.startsWith(Hashes.blowfish_gen2));
        assertTrue(BCrypt.checkpw(password, hash));
    }

    @Test
    void testGenerateHashWithPrefix(){
        String hash = JenkinsHash.generateHashWithPrefix(password);
        assertTrue(hash.startsWith(Hashes.jenkins_bcrypt));
        assertTrue(BCrypt.checkpw(password, hash.substring(JenkinsHash.jbcrypt.length(), hash.length())));
    }

}
