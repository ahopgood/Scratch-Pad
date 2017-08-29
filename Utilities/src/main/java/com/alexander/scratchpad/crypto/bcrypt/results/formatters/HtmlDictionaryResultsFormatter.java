package com.alexander.scratchpad.crypto.bcrypt.results.formatters;

import com.alexander.scratchpad.crypto.bcrypt.results.DictionaryResult;

import java.util.Collections;
import java.util.List;

import static com.alexander.scratchpad.crypto.bcrypt.results.formatters.Html.*;

/**
 * Created by Alexander on 21/08/2017.
 */
public class HtmlDictionaryResultsFormatter implements DictionaryResultsFormatter {

    @Override
    public String format(List<DictionaryResult> results) {
        StringBuilder output = new StringBuilder();
        
        output.append(TABLE_OPEN+ NEWLINE+ TAB);
        output.append(ROW_OPEN);
        output.append(HEADER_OPEN+"Cost"+ HEADER_CLOSE);
        if (!results.isEmpty()){
            Collections.sort(results.get(0).getIndex());
            for (Integer key : results.get(0).getIndex()){
                output.append(HEADER_OPEN+key+ HEADER_CLOSE);
            }
        }
        output.append(ROW_CLOSE+ NEWLINE);
        
        results.forEach(result -> {
            output.append(TAB+ ROW_OPEN+ COLUMN_OPEN);
            output.append(result.getCostFactor()+ COLUMN_CLOSE);

            Collections.sort(result.getIndex());
            for (Integer key : result.getIndex()){
                output.append(COLUMN_OPEN+ Formatting.getFormattedTime(result.getResults().get(key))+ COLUMN_CLOSE);
            }
            output.append(ROW_CLOSE+ NEWLINE);
        });
        
        output.append(TABLE_CLOSE);
        output.append(NEWLINE);
        return output.toString();
    }
}
