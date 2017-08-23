package com.alexander.scratchpad.crypto.bcrypt.results.formatters;

import com.alexander.scratchpad.crypto.bcrypt.results.BenchmarkResult;

import java.util.List;

/**
 * Created by Alexander on 13/08/2017.
 */
public interface BenchmarkResultsFormatter {
    
    String format(List<BenchmarkResult> results);
}
