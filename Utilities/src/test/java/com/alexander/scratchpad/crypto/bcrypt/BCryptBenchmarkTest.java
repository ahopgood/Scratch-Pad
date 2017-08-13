package com.alexander.scratchpad.crypto.bcrypt;

import com.alexander.scratchpad.conversion.BCryptHash;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    
    @Test (expected = BCryptHashException.class)
    public void testRunBenchmark_costFactorEndGreaterThan31_thenThrowException() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(0, BCryptHash.maximumCost+1);
    }

    @Test (expected = BCryptHashException.class)
    public void testRunBenchmark_costFactorStartGreaterThan31_thenThrowException() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(BCryptHash.maximumCost+1, BCryptHash.maximumCost);
    }

    @Test (expected = BCryptHashException.class)
    public void testRunBenchmark_costFactorEndLessThan0_thenThrowException() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(0, -1);
    }
    
    @Test (expected = BCryptHashException.class)
    public void testRunBenchmark_costFactorStartLessThan0_thenThrowException() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(-1, BCryptHash.maximumCost);
    }

    @Test (expected = BCryptHashException.class)
    public void testRunBenchmark_costFactorStartIsGreaterThanCostFactorEnd_thenThrowException() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(2, 1);
    }

    @Test
    public void testRunBenchmark_costFactorDifferenceOfZero_thenReturnSingleResults() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5,5);
        assertNotNull(bench.runBenchmark());
        assertEquals(1,bench.runBenchmark().size());
    }
    
    @Test
    public void testRunBenchmark_costFactorDifferenceOfFour_thenReturnFiveResults() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5,9);
        assertNotNull(bench.runBenchmark());
        assertEquals(5, bench.runBenchmark().size());
    }

    @Test
    public void testRunBenchmark_verifyCostFactorAndEffortIncreases() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5,9);
        assertNotNull(bench.runBenchmark());
        assertEquals(5, bench.runBenchmark().size());
        
        
    }
    
    @Test
    public void testRunBenchmark_() throws BCryptHashException {
        BCryptBenchmark bench = new BCryptBenchmark(5,14);
        List<BenchmarkResult> results = bench.runBenchmark();
        assertNotNull(results);
        assertEquals(10, results.size());
        

        bench.printBenchmark(new BenchmarkResultsFormatter() {
            @Override
            public String format(List<BenchmarkResult> results) {
                StringBuilder output = new StringBuilder();
                results.forEach(result -> {
                    String resultString = "Cost:"+result.getCostFactor()+"\t Time:"+(result.getFinish() - result.getStart())+"ms\n";
                    System.out.print(resultString);
                    output.append(resultString);
                } );
                return output.toString();
            }
        }, results);
    }
}