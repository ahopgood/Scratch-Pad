package com.alexander.scratchpad.conversion;

public class StringSanitiser {

    public static String sanitise(String input) {
        return input.replace("\n","").replace("\r","");
    }
}

