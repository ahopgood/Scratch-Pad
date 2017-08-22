package com.alexander.scratchpad.crypto.bcrypt;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Alexander on 13/08/2017.
 */
public class DictionaryResult {
    
    private int costFactor;
    private Map<Integer, Long> results;
    private List<Integer> index = new LinkedList<>();
    
    public DictionaryResult(int costFactor, Map<Integer, Long> results){
        this.costFactor = costFactor;
        this.results = results;
        index.addAll(results.keySet().stream().collect(Collectors.toList()));
    }

    public int getCostFactor() {
        return costFactor;
    }

    public Map<Integer, Long> getResults() {
        return results;
    }

    public List<Integer> getIndex() { return index; }
}
