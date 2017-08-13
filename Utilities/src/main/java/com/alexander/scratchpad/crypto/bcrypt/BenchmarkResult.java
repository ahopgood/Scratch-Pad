package com.alexander.scratchpad.crypto.bcrypt;

/**
 * Created by Alexander on 13/08/2017.
 */
public class BenchmarkResult {
    private long start;
    private long finish;
    private int costFactor;
    
    BenchmarkResult(long start, long finish, int costFactor){
        this.start = start;
        this.finish = finish;
        this.costFactor = costFactor;
    }

    public long getStart() {
        return start;
    }

    public long getFinish() {
        return finish;
    }

    public int getCostFactor() {
        return costFactor;
    }
}
