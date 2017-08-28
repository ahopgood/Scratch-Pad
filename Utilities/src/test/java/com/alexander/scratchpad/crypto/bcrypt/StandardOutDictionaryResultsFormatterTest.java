package com.alexander.scratchpad.crypto.bcrypt;

import com.alexander.scratchpad.crypto.bcrypt.results.BenchmarkResult;
import com.alexander.scratchpad.crypto.bcrypt.results.DictionaryResult;
import com.alexander.scratchpad.crypto.bcrypt.results.formatters.Formatting;
import com.alexander.scratchpad.crypto.bcrypt.results.formatters.HtmlDictionaryResultsFormatter;
import com.alexander.scratchpad.crypto.bcrypt.results.formatters.StandardOutDictionaryResultsFormatter;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
        assertEquals("0 hr, 0 min, 0 sec, 200 ms", Formatting.getFormattedTime(200));
    }

    @Test
    public void testSeconds(){
        StandardOutDictionaryResultsFormatter test = new StandardOutDictionaryResultsFormatter();
        assertEquals("0 hr, 0 min, 2 sec, 200 ms", Formatting.getFormattedTime(2_200));
    }

    @Test
    public void testMinutes(){
        StandardOutDictionaryResultsFormatter test = new StandardOutDictionaryResultsFormatter();
        assertEquals("0 hr, 2 min, 2 sec, 200 ms", Formatting.getFormattedTime(122_200));
    }

    @Test
    public void testHours(){
        StandardOutDictionaryResultsFormatter test = new StandardOutDictionaryResultsFormatter();
        assertEquals("2 hr, 2 min, 2 sec, 200 ms", Formatting.getFormattedTime(7_322_200));
    }

    @Test
    public void testDays(){
        StandardOutDictionaryResultsFormatter test = new StandardOutDictionaryResultsFormatter();
        assertEquals("2 hr, 2 min, 2 sec, 200 ms", Formatting.getFormattedTime(7_322_200));
    }

    @Test
    public void testPrintDictionaryBenchmark_givenBenchmarkResultList_whenDictionaryListHasThreeValues() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        List<DictionaryResult> dictionaryResult = bench.getDictionaryBenchmark(results, Arrays.asList(100,1000,10000));
        assertEquals(1, dictionaryResult.size());
        assertEquals(3, dictionaryResult.get(0).getResults().size());

        assertEquals(new Long(1000000), dictionaryResult.get(0).getResults().get(100));
        assertEquals(new Long(10000000), dictionaryResult.get(0).getResults().get(1000));
        assertEquals(new Long(100000000), dictionaryResult.get(0).getResults().get(10000));

        String output = bench.printDictionaryResults(new StandardOutDictionaryResultsFormatter(), dictionaryResult);
        System.out.println(output);
    }
}