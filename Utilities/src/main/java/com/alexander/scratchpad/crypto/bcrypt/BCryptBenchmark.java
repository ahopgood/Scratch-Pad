package com.alexander.scratchpad.crypto.bcrypt;

import com.alexander.scratchpad.conversion.BCryptHash;
import com.alexander.scratchpad.crypto.bcrypt.results.BenchmarkResult;
import com.alexander.scratchpad.crypto.bcrypt.results.DictionaryResult;
import com.alexander.scratchpad.crypto.bcrypt.results.formatters.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.*;

/**
 * Created by Alexander on 13/08/2017.
 */
public class BCryptBenchmark {

    private static int targetUserGroupSize = 100;
    
    public static void main(String[] args){

        String validation = validate(args);
        if (validation.isEmpty()){
            String details = validationDetail(args);
            if (details.isEmpty()){
                try {
                    int startCost = Integer.valueOf(args[0]);
                    int finishCost = Integer.valueOf(args[1]);
                    BCryptBenchmark bench = new BCryptBenchmark(startCost, finishCost);

                    List<BenchmarkResult> results = bench.runBenchmark();
                    REPORT benchReport = REPORT.valueOf(args[2]);
                    switch (benchReport) {
                        case STDOUT:
                            bench.printBenchmark(new StandardOutBenchmarkResultsFormatter(), results);
                            break;
                        case HTML:
                            bench.printBenchmark(new HtmlBenchmarkResultsFormatter(), results);
                            break;
                    }
                    List<DictionaryResult> dictResults = bench.getDictionaryBenchmark(results, Arrays.asList(100,1_000, 10_000));
                    if (args.length == 4){
                        REPORT dictionaryReport = REPORT.valueOf(args[3]);
                        switch (dictionaryReport){
                            case STDOUT:
                                System.out.println(bench.printDictionaryResults(new StandardOutDictionaryResultsFormatter(), dictResults));
                                break;
                            case HTML:
                                System.out.println(bench.printDictionaryResults(new HtmlDictionaryResultsFormatter(), dictResults));
                                break;
                        }
                    }
                    
                } catch (BCryptHashException e) {
                    System.err.println(e.getMessage());
                }
            } else {
                System.err.print(validation);
            }
        } else {
            System.err.print(validation);
        }
    }
    
    enum REPORT {
        HTML, STDOUT;
    }
    
    protected static String buildHelp(){
        StringBuilder builder = new StringBuilder();
        builder.append("There are incorrect parameters.\n");
        builder.append("BCryptBenchmark.jar ");
        builder.append(" <start_cost_factor>");
        builder.append(" <end_cost_factor>");
        builder.append(" <benchmark_report_format>");
        builder.append(" <dictionary_report_format> (optional)\n");

        builder.append("<start_cost_factor> Integer representing the starting cost factor to benchmark from\n");
        builder.append("<end_cost_factor> Integer representing the ending cost factor to benchmark against\n");
        builder.append("<benchmark_report_format> String representing the report format; html, stdout. Defaults to standard out\n");
        builder.append("<dictionary_report_format> Optional string, if present then a dictionary benchmark report is generated; html, stdout. Defaults to standard out\n");
        return builder.toString();
    }
    
    public static String validate(String[] args){
        if (args == null || args.length < 3 || args.length > 4){
            return buildHelp();
        }
        return "";
    }

    public static String validationDetail(String[] args){
        int first = 0;
        int second = 1;
        int third = 2;
        int fourth = 3;
        try {
            Integer.valueOf(args[first]);
        } catch (NumberFormatException nfe) {
            return "Parameter "+first+" ["+args[first]+"] is not a integer";
        }
        try {
            Integer.valueOf(args[second]);
        } catch (NumberFormatException nfe){
            return "Parameter "+second+" ["+args[second]+" is not an integer";
        }
        try {
            REPORT.valueOf(args[third]);
        } catch (IllegalArgumentException iae){
            return "Parameter "+third+" ["+args[third]+" does not match "+REPORT.HTML.name()+" or "+REPORT.STDOUT.name();
        }
        if (args.length == 4) {
            try {
                REPORT.valueOf(args[fourth]);
            } catch (IllegalArgumentException iae) {
                return "Parameter " + fourth + " [" + args[fourth] + " does not match " + REPORT.HTML.name() + " or " + REPORT.STDOUT.name();
            }
        }
        return "";
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
