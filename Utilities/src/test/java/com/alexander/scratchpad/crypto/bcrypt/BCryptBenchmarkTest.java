package com.alexander.scratchpad.crypto.bcrypt;

import com.alexander.scratchpad.conversion.BCryptHash;
import com.alexander.scratchpad.crypto.bcrypt.results.BenchmarkResult;
import com.alexander.scratchpad.crypto.bcrypt.results.DictionaryResult;
import com.alexander.scratchpad.crypto.bcrypt.results.formatters.HtmlBenchmarkResultsFormatter;
import com.alexander.scratchpad.crypto.bcrypt.results.formatters.HtmlDictionaryResultsFormatter;
import com.alexander.scratchpad.crypto.bcrypt.results.formatters.StandardOutBenchmarkResultsFormatter;
import com.alexander.scratchpad.crypto.bcrypt.results.formatters.StandardOutDictionaryResultsFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Alexander on 13/08/2017.
 */
public class BCryptBenchmarkTest {

    @Test
    void testRunBenchmark_costFactorEndGreaterThan31_thenThrowException()  {
        assertThrows(BCryptHashException.class, () -> new BCryptBenchmark(0, BCryptHash.maximumCost + 1));
    }

    @Test
    void testRunBenchmark_costFactorStartGreaterThan31_thenThrowException() {
        assertThrows(BCryptHashException.class, () ->new BCryptBenchmark(BCryptHash.maximumCost + 1, BCryptHash.maximumCost));
    }

    @Test
    void testRunBenchmark_costFactorEndLessThan0_thenThrowException() {
        assertThrows(BCryptHashException.class, () ->new BCryptBenchmark(0, -1));
    }

    @Test
    void testRunBenchmark_costFactorStartLessThan0_thenThrowException() {
        assertThrows(BCryptHashException.class, () ->new BCryptBenchmark(-1, BCryptHash.maximumCost));
    }

    @Test
    void testRunBenchmark_costFactorStartIsGreaterThanCostFactorEnd_thenThrowException() {
        assertThrows(BCryptHashException.class, () -> new BCryptBenchmark(2, 1));
    }

    @Test
    void testRunBenchmark_costFactorDifferenceOfZero_thenReturnSingleResults() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 5);
        assertNotNull(bench.runBenchmark());
        assertEquals(1, bench.runBenchmark().size());
    }

    @Test
    void testRunBenchmark_costFactorDifferenceOfFour_thenReturnFiveResults() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 9);
        assertNotNull(bench.runBenchmark());
        assertEquals(5, bench.runBenchmark().size());
    }

    @Test
    void testRunBenchmark_verifyCostFactorAndEffortIncreases() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 9);
        assertNotNull(bench.runBenchmark());
        assertEquals(5, bench.runBenchmark().size());


    }

    @Test
    void testRunBenchmark_withConsoleFormatter() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 14);
        List<BenchmarkResult> results = bench.runBenchmark();
        assertNotNull(results);
        assertEquals(10, results.size());
        
        bench.printBenchmark(new StandardOutBenchmarkResultsFormatter(), results);
    }

    @Test
    void testRunBenchmark_withHtmlFormatter() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 14);
        List<BenchmarkResult> results = bench.runBenchmark();
        assertNotNull(results);
        assertEquals(10, results.size());
        
        bench.printBenchmark(new HtmlBenchmarkResultsFormatter(), results);
    }

    @Test
    void testGetDictionaryBenchmark_givenNullBenchmarkResult() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        Long dictionaryResult = bench.getDictionaryBenchmark(null, 100);
        assertEquals(new Long(0), dictionaryResult);
    }

//    @Test
//    public void testGetDictionaryBenchmark_givenEmptyBenchmarkResult() throws BCryptHashException {
//        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
//        List<BenchmarkResult> results = new LinkedList<>();
//        results.add(new BenchmarkResult(0, 100, 10));
//
//        List<DictionaryResult> dictionaryResults = bench.getDictionaryBenchmark(new LinkedList<>(), 100);
//        assertNotNull(dictionaryResults);
//        assertEquals(true, dictionaryResults.isEmpty());
//    }

    @Test
    void testGetDictionaryBenchmark_givenBenchmarkResult_whenDictionaryIs0() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        Long dictionaryResult = bench.getDictionaryBenchmark(results.get(0), 0);
        assertEquals(new Long(0), dictionaryResult);
    }

    @Test
    void testGetDictionaryBenchmark_givenBenchmarkResult_whenDictionaryIsNegative() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        Long dictionaryResult = bench.getDictionaryBenchmark(results.get(0), -1);
        assertEquals(new Long(0), dictionaryResult);
    }

    @Test
    void testGetDictionaryBenchmark_givenBenchmarkResult_whenDictionaryIs100() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        Long dictionaryResult = bench.getDictionaryBenchmark(results.get(0), 100);
        assertEquals(new Long(1000000), dictionaryResult);
    }

    @Test
    void testGetDictionaryBenchmark_givenBenchmarkResult_whenDictionaryIs1000() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        Long dictionaryResult = bench.getDictionaryBenchmark(results.get(0), 1000);
        assertEquals(new Long(10000000), dictionaryResult);
    }

    @Test
    void testGetDictionaryBenchmark_givenBenchmarkResult_whenDictionaryIs10000() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        Long dictionaryResult = bench.getDictionaryBenchmark(results.get(0), 10000);
        assertEquals(new Long(100000000), dictionaryResult);
    }

    @Test
    void testGetDictionaryBenchmark_givenBenchmarkResultListIsNull() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        assertThrows(BCryptHashException.class, () -> bench.getDictionaryBenchmark(null, Arrays.asList(100,1000,10000)));
    }

    @Test
    void testGetDictionaryBenchmark_givenBenchmarkResultList_whenDictionaryListIsNull() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        assertThrows(BCryptHashException.class, () -> bench.getDictionaryBenchmark(results, null));
    }

    @Test
    void testGetDictionaryBenchmark_givenBenchmarkResultListIsEmpty() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        List<DictionaryResult> dictionaryResult = bench.getDictionaryBenchmark(new LinkedList<>(), Arrays.asList(100,1000,10000));
        assertEquals(true, dictionaryResult.isEmpty());
    }

    @Test
    void testGetDictionaryBenchmark_givenBenchmarkResultList_whenDictionaryListIsEmpty() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        List<DictionaryResult> dictionaryResult = bench.getDictionaryBenchmark(results, new LinkedList<>());
        assertEquals(1, dictionaryResult.size());
        assertEquals(true, dictionaryResult.get(0).getResults().isEmpty());
    }

    @Test
    void testGetDictionaryBenchmark_givenBenchmarkResultList_whenDictionaryListHasThreeValues() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        List<DictionaryResult> dictionaryResult = bench.getDictionaryBenchmark(results, Arrays.asList(100,1000,10000));
        assertEquals(1, dictionaryResult.size());
        assertEquals(3, dictionaryResult.get(0).getResults().size());

        assertEquals(new Long(1000000), dictionaryResult.get(0).getResults().get(100));
        assertEquals(new Long(10000000), dictionaryResult.get(0).getResults().get(1000));
        assertEquals(new Long(100000000), dictionaryResult.get(0).getResults().get(10000));
    }
    
    @Test
    void testPrintDictionaryBenchmarkToConsole_givenBenchmarkResultList_whenDictionaryListHasThreeValues() throws BCryptHashException {
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
    @Test
    void testPrintDictionaryBenchmakToHTML_givenBenchmarkResultList_whenDictionaryListHasThreeValues() throws BCryptHashException {
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
        System.out.println(output);
    }

    @Test
    void testValidate_givenNullArgs(){

      String[] args = null;
        assertEquals(BCryptBenchmark.buildHelp(), BCryptBenchmark.validate(args));
    }
}
