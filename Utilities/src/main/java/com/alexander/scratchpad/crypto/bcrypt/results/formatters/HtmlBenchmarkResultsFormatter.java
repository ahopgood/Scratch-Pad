package com.alexander.scratchpad.crypto.bcrypt.results.formatters;

import com.alexander.scratchpad.crypto.bcrypt.results.BenchmarkResult;

import java.util.List;

import static com.alexander.scratchpad.crypto.bcrypt.results.formatters.Html.*;

/**
 * Created by Alexander on 22/08/2017.
 */
public class HtmlBenchmarkResultsFormatter implements BenchmarkResultsFormatter {
    @Override
    public String format(List<BenchmarkResult> results) {
        StringBuilder output = new StringBuilder();

        output.append(TABLE_OPEN);
        output.append(NEWLINE);
        output.append(TAB);
        output.append(ROW_OPEN);
        output.append(HEADER_OPEN);
        output.append("Cost");
        output.append(HEADER_CLOSE);
        output.append(HEADER_OPEN);
        output.append("Time");
        output.append(HEADER_CLOSE);
        output.append(ROW_CLOSE);
        output.append(NEWLINE);
        
        System.out.print(output.toString());
        results.forEach(result -> {
            String resultString = 
                    TAB+
                    ROW_OPEN+ 
                    COLUMN_OPEN+
                    result.getCostFactor()+
                    COLUMN_CLOSE+
                    COLUMN_OPEN+
                    (result.getFinish() - result.getStart())+"ms"+
                    COLUMN_CLOSE+
                    ROW_CLOSE+
                    NEWLINE;
//            String resultString = "\t<tr><td>" + result.getCostFactor() + "</td><td>" + (result.getFinish() - result.getStart()) + "ms</td></tr>\n";
            System.out.print(resultString);
            output.append(resultString);
        });

        String close = TABLE_CLOSE+
                NEWLINE;
        System.out.print(close);
//        output.append(close);
        return output.toString();
    }
}
