package com.alexander.scratchpad.crypto.bcrypt;

import java.util.Map;

/**
 * Created by Alexander on 13/08/2017.
 */
public class DictionaryResult {
    
    private int costFactor;
    private Map<Integer, Long> results;
    
    public DictionaryResult(int costFactor, Map<Integer, Long> results){
        this.costFactor = costFactor;
        this.results = results;
    }

    public int getCostFactor() {
        return costFactor;
    }

    public Map<Integer, Long> getResults() {
        return results;
    }
}
