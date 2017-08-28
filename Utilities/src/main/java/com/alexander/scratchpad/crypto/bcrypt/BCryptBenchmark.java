package com.alexander.scratchpad.crypto.bcrypt;

import com.alexander.scratchpad.conversion.BCryptHash;
import com.alexander.scratchpad.crypto.bcrypt.results.BenchmarkResult;
import com.alexander.scratchpad.crypto.bcrypt.results.DictionaryResult;
import com.alexander.scratchpad.crypto.bcrypt.results.formatters.BenchmarkResultsFormatter;
import com.alexander.scratchpad.crypto.bcrypt.results.formatters.DictionaryResultsFormatter;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexander on 13/08/2017.
 */
public class BCryptBenchmark {

    private static int targetUserGroupSize = 100;
    
    public static void main(String[] args){
        if (args.length < 2){
            System.err.println("Parameters are:");
            System.err.println("Start Cost Factor");
        }
    }
    
    private final String testPassword = "security[]./?IS/1mp0rtant";
    private int startCostFactor = 0;
    private int endCostFactor = 0;
    
    BCryptBenchmark(int startCostFactor, int endCostFactor) throws BCryptHashException {
        if (startCostFactor > BCryptHash.maximumCost ||endCostFactor > BCryptHash.maximumCost){
            throw new BCryptHashException("BCrypt cost factor cannot be greater than "+BCryptHash.maximumCost);
        }
        if (startCostFactor < 0 || endCostFactor < 0){
            throw new BCryptHashException("BCrypt cost factor cannot be less than zero, you set startCostFactor ["+startCostFactor+"] and endCostFactor ["+endCostFactor+"]");
        }
        if (startCostFactor > endCostFactor){
            throw new BCryptHashException("The start cost factor needs to be greater than or equal to the end cost factor");
        }
        this.startCostFactor = startCostFactor;
        this.endCostFactor = endCostFactor;
    }
    
    public List<BenchmarkResult> runBenchmark(){
        //Run once to remove initial setup cost
        BCrypt.hashpw(testPassword, BCrypt.gensalt(5));
        List<BenchmarkResult> results = new LinkedList<>();
        for (int i = this.startCostFactor; i <= this.endCostFactor; i++){
            long start = getCurrentTime();
            BCrypt.hashpw(testPassword, BCrypt.gensalt(i));
            long end = getCurrentTime();
            results.add(new BenchmarkResult(start, end, i));
        }
        return results;
    }
    
    public long getCurrentTime(){
        return System.currentTimeMillis();
    }
    
    public String printBenchmark(BenchmarkResultsFormatter formatter, List<BenchmarkResult> results){
        return formatter.format(results);
    }
    
    public List<DictionaryResult> getDictionaryBenchmark(List<BenchmarkResult> benchmarkResults, List<Integer> dictionarySizes) throws BCryptHashException {
        if (benchmarkResults == null){
            throw new BCryptHashException("BenchmarkResult list is null, cannot generate dictionary benchmark results.");
        }
        if (dictionarySizes == null){
            throw new BCryptHashException("Dictionary size list is null, cannot generate dictionary benchmark results.");            
        }
        List<DictionaryResult> dictionaryResults = new LinkedList<>();
        for (BenchmarkResult benchmarkResult : benchmarkResults){
            Map<Integer, Long> dictionaryResultMap = new HashMap<>();
            for (Integer dictionarySize : dictionarySizes){
                dictionaryResultMap.put(dictionarySize, getDictionaryBenchmark(benchmarkResult, dictionarySize));
            }
            dictionaryResults.add(new DictionaryResult(benchmarkResult.getCostFactor(), dictionaryResultMap));
        }
        return dictionaryResults;
    }
    
    public Long getDictionaryBenchmark(BenchmarkResult benchmarkResult, Integer dictionarySize){
        if (dictionarySize <= 0){
            return 0L;
        }
        if (benchmarkResult == null){
            return 0L;
        }
        return (benchmarkResult.getFinish() - benchmarkResult.getStart()) * dictionarySize * this.targetUserGroupSize;
    }

    public String printDictionaryResults(DictionaryResultsFormatter formatter, List<DictionaryResult> results){
        return formatter.format(results);
    }

}
