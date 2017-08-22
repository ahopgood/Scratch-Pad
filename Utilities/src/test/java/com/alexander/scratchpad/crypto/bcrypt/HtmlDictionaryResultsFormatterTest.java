package com.alexander.scratchpad.crypto.bcrypt;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Alexander on 21/08/2017.
 */
public class HtmlDictionaryResultsFormatterTest {

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

        String output = bench.printDictionaryResults(new HtmlDictionaryResultsFormatter(), dictionaryResult);
    }
}