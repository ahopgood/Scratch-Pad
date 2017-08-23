package com.alexander.scratchpad.crypto.bcrypt.results.formatters;

import com.alexander.scratchpad.crypto.bcrypt.results.BenchmarkResult;

import java.util.List;

/**
 * Created by Alexander on 22/08/2017.
 */
public class HtmlBenchmarkResultsFormatter implements BenchmarkResultsFormatter {
    @Override
    public String format(List<BenchmarkResult> results) {
        StringBuilder output = new StringBuilder();

        output.append(HtmlDictionaryResultsFormatter.TABLE_OPEN);
        output.append(HtmlDictionaryResultsFormatter.NEWLINE);
        output.append(HtmlDictionaryResultsFormatter.TAB);
        output.append(HtmlDictionaryResultsFormatter.ROW_OPEN);
        output.append(HtmlDictionaryResultsFormatter.HEADER_OPEN);
        output.append("Cost");
        output.append(HtmlDictionaryResultsFormatter.HEADER_CLOSE);
        output.append(HtmlDictionaryResultsFormatter.HEADER_OPEN);
        output.append("Time");
        output.append(HtmlDictionaryResultsFormatter.HEADER_CLOSE);
        output.append(HtmlDictionaryResultsFormatter.ROW_CLOSE);
        output.append(HtmlDictionaryResultsFormatter.NEWLINE);
        
        System.out.print(output.toString());
        results.forEach(result -> {
            String resultString = 
                    HtmlDictionaryResultsFormatter.TAB+
                    HtmlDictionaryResultsFormatter.ROW_OPEN+ 
                    HtmlDictionaryResultsFormatter.COLUMN_OPEN+
                    result.getCostFactor()+
                    HtmlDictionaryResultsFormatter.COLUMN_CLOSE+
                    HtmlDictionaryResultsFormatter.COLUMN_OPEN+
                    (result.getFinish() - result.getStart())+"ms"+
                    HtmlDictionaryResultsFormatter.COLUMN_CLOSE+
                    HtmlDictionaryResultsFormatter.ROW_CLOSE+
                    HtmlDictionaryResultsFormatter.NEWLINE;
//            String resultString = "\t<tr><td>" + result.getCostFactor() + "</td><td>" + (result.getFinish() - result.getStart()) + "ms</td></tr>\n";
            System.out.print(resultString);
            output.append(resultString);
        });

        String close = HtmlDictionaryResultsFormatter.TABLE_CLOSE+
                HtmlDictionaryResultsFormatter.NEWLINE;
        System.out.print(close);
//        output.append(close);
        return output.toString();
    }
}
