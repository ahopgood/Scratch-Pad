package com.alexander.scratchpad.crypto.bcrypt.results.formatters;

import com.alexander.scratchpad.crypto.bcrypt.results.BenchmarkResult;

import java.util.List;

/**
 * Created by Alexander on 22/08/2017.
 */
public class StandardOutBenchmarkResultsFormatter implements BenchmarkResultsFormatter{
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
}
