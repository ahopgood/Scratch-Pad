package com.alexander.scratchpad.crypto.bcrypt;

import com.alexander.scratchpad.conversion.BCryptHash;
import org.mindrot.jbcrypt.BCrypt;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander on 13/08/2017.
 */
public class BCryptBenchmark {

    public static void main(String[] args){
        
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
}
