package com.alexander.scratchpad.crypto.bcrypt;

import java.util.List;

/**
 * Created by Alexander on 13/08/2017.
 */
public interface BenchmarkResultsFormatter {
    
    String format(List<BenchmarkResult> results);
}
