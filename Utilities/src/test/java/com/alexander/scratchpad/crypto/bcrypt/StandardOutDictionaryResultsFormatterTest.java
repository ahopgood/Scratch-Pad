package com.alexander.scratchpad.crypto.bcrypt;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alexander on 22/08/2017.
 */
public class StandardOutDictionaryResultsFormatterTest {
    int second = 1000;
    int minute = 60 * second;
    int hour = 60 * minute;
    int day = 24 * hour;
    int week = 7 * day;
    
    @Test
    public void testMillis(){
        StandardOutDictionaryResultsFormatter test = new StandardOutDictionaryResultsFormatter();
        assertEquals("0 hr, 0 min, 0 sec, 200 ms", test.getFormatted(200));
    }

    @Test
    public void testSeconds(){
        StandardOutDictionaryResultsFormatter test = new StandardOutDictionaryResultsFormatter();
        assertEquals("0 hr, 0 min, 2 sec, 200 ms", test.getFormatted(2_200));
    }

    @Test
    public void testMinutes(){
        StandardOutDictionaryResultsFormatter test = new StandardOutDictionaryResultsFormatter();
        assertEquals("0 hr, 2 min, 2 sec, 200 ms", test.getFormatted(122_200));
    }

    @Test
    public void testHours(){
        StandardOutDictionaryResultsFormatter test = new StandardOutDictionaryResultsFormatter();
        assertEquals("2 hr, 2 min, 2 sec, 200 ms", test.getFormatted(7_322_200));
    }

    @Test
    public void testDays(){
        StandardOutDictionaryResultsFormatter test = new StandardOutDictionaryResultsFormatter();
        assertEquals("2 hr, 2 min, 2 sec, 200 ms", test.getFormatted(7_322_200));
    }
}