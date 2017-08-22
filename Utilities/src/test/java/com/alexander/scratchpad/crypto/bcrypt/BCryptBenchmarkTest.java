package com.alexander.scratchpad.crypto.bcrypt;

import com.alexander.scratchpad.conversion.BCryptHash;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Alexander on 13/08/2017.
 */
public class BCryptBenchmarkTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = BCryptHashException.class)
    public void testRunBenchmark_costFactorEndGreaterThan31_thenThrowException() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(0, BCryptHash.maximumCost + 1);
    }

    @Test(expected = BCryptHashException.class)
    public void testRunBenchmark_costFactorStartGreaterThan31_thenThrowException() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(BCryptHash.maximumCost + 1, BCryptHash.maximumCost);
    }

    @Test(expected = BCryptHashException.class)
    public void testRunBenchmark_costFactorEndLessThan0_thenThrowException() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(0, -1);
    }

    @Test(expected = BCryptHashException.class)
    public void testRunBenchmark_costFactorStartLessThan0_thenThrowException() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(-1, BCryptHash.maximumCost);
    }

    @Test(expected = BCryptHashException.class)
    public void testRunBenchmark_costFactorStartIsGreaterThanCostFactorEnd_thenThrowException() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(2, 1);
    }

    @Test
    public void testRunBenchmark_costFactorDifferenceOfZero_thenReturnSingleResults() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 5);
        assertNotNull(bench.runBenchmark());
        assertEquals(1, bench.runBenchmark().size());
    }

    @Test
    public void testRunBenchmark_costFactorDifferenceOfFour_thenReturnFiveResults() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 9);
        assertNotNull(bench.runBenchmark());
        assertEquals(5, bench.runBenchmark().size());
    }

    @Test
    public void testRunBenchmark_verifyCostFactorAndEffortIncreases() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 9);
        assertNotNull(bench.runBenchmark());
        assertEquals(5, bench.runBenchmark().size());


    }

    @Test
    public void testRunBenchmark_withConsoleFormatter() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 14);
        List<BenchmarkResult> results = bench.runBenchmark();
        assertNotNull(results);
        assertEquals(10, results.size());


        bench.printBenchmark(new BenchmarkResultsFormatter() {
            @Override
            public String format(List<BenchmarkResult> results) {
                StringBuilder output = new StringBuilder();
                results.forEach(result -> {
                    String resultString = "Cost:" + result.getCostFactor() + "\t Time:" + (result.getFinish() - result.getStart()) + "ms\n";
                    System.out.print(resultString);
                    output.append(resultString);
                });
                return output.toString();
            }
        }, results);
    }

    @Test
    public void testRunBenchmark_withHtmlFormatter() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 14);
        List<BenchmarkResult> results = bench.runBenchmark();
        assertNotNull(results);
        assertEquals(10, results.size());


        bench.printBenchmark(new BenchmarkResultsFormatter() {
            @Override
            public String format(List<BenchmarkResult> results) {
                StringBuilder output = new StringBuilder();

                String headerString = "<table>\n\t<tr><th>Cost</th><th>Time</th></tr>\n";
                System.out.print(headerString);
                output.append(headerString);

                results.forEach(result -> {
                    String resultString = "\t<tr><td>" + result.getCostFactor() + "</td><td>" + (result.getFinish() - result.getStart()) + "ms</td></tr>\n";
                    System.out.print(resultString);
                    output.append(resultString);
                });

                String close = "</table>\n";
                System.out.print(close);
                output.append(close);
                return output.toString();
            }
        }, results);
    }

    @Test
    public void testGetDictionaryBenchmark_givenNullBenchmarkResult() throws BCryptHashException {
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
    public void testGetDictionaryBenchmark_givenBenchmarkResult_whenDictionaryIs0() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        Long dictionaryResult = bench.getDictionaryBenchmark(results.get(0), 0);
        assertEquals(new Long(0), dictionaryResult);
    }

    @Test
    public void testGetDictionaryBenchmark_givenBenchmarkResult_whenDictionaryIsNegative() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        Long dictionaryResult = bench.getDictionaryBenchmark(results.get(0), -1);
        assertEquals(new Long(0), dictionaryResult);
    }

    @Test
    public void testGetDictionaryBenchmark_givenBenchmarkResult_whenDictionaryIs100() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        Long dictionaryResult = bench.getDictionaryBenchmark(results.get(0), 100);
        assertEquals(new Long(1000000), dictionaryResult);
    }

    @Test
    public void testGetDictionaryBenchmark_givenBenchmarkResult_whenDictionaryIs1000() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        Long dictionaryResult = bench.getDictionaryBenchmark(results.get(0), 1000);
        assertEquals(new Long(10000000), dictionaryResult);
    }

    @Test
    public void testGetDictionaryBenchmark_givenBenchmarkResult_whenDictionaryIs10000() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        Long dictionaryResult = bench.getDictionaryBenchmark(results.get(0), 10000);
        assertEquals(new Long(100000000), dictionaryResult);
    }

    @Test
    public void testGetDictionaryBenchmark_givenBenchmarkResultListIsNull() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        List<DictionaryResult> dictionaryResult = bench.getDictionaryBenchmark(null, Arrays.asList(100,1000,10000));
        assertEquals(true, dictionaryResult.isEmpty());
    }

    @Test
    public void testGetDictionaryBenchmark_givenBenchmarkResultList_whenDictionaryListIsNull() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        List<DictionaryResult> dictionaryResult = bench.getDictionaryBenchmark(results, null);
        assertEquals(true, dictionaryResult.isEmpty());
    }

    @Test
    public void testGetDictionaryBenchmark_givenBenchmarkResultListIsEmpty() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        List<DictionaryResult> dictionaryResult = bench.getDictionaryBenchmark(new LinkedList<>(), Arrays.asList(100,1000,10000));
        assertEquals(true, dictionaryResult.isEmpty());
    }

    @Test
    public void testGetDictionaryBenchmark_givenBenchmarkResultList_whenDictionaryListIsEmpty() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5, 6);
        List<BenchmarkResult> results = new LinkedList<>();
        results.add(new BenchmarkResult(0, 100, 10));

        List<DictionaryResult> dictionaryResult = bench.getDictionaryBenchmark(results, new LinkedList<>());
        assertEquals(true, dictionaryResult.isEmpty());
    }

    @Test
    public void testGetDictionaryBenchmark_givenBenchmarkResultList_whenDictionaryListHasThreeValues() throws BCryptHashException {
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
    public void testPrintDictionaryBenchmarkToConsole_givenBenchmarkResultList_whenDictionaryListHasThreeValues() throws BCryptHashException {
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
    public void testPrintDictionaryBenchmakToHTML_givenBenchmarkResultList_whenDictionaryListHasThreeValues() throws BCryptHashException {
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

}