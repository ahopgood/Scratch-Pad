package com.alexander.scratchpad.crypto.bcrypt;

import java.util.Collections;
import java.util.List;

/**
 * Created by Alexander on 21/08/2017.
 */
public class HtmlDictionaryResultsFormatter implements DictionaryResultsFormatter {
    
    public static final String TABLE_OPEN   = "<table>";
    public static final String TABLE_CLOSE  = "</table>";
    public static final String ROW_OPEN     = "<tr>";
    public static final String ROW_CLOSE    = "</tr>";
    public static final String HEADER_OPEN  = "<th>";
    public static final String HEADER_CLOSE = "</th>";
    public static final String COLUMN_OPEN  = "<td>";
    public static final String COLUMN_CLOSE = "</td>";
    public static final String NEWLINE      = "\n";
    public static final String TAB          = "\t";
    
    @Override
    public String format(List<DictionaryResult> results) {
        StringBuilder output = new StringBuilder();
        
        output.append(TABLE_OPEN+NEWLINE+TAB);
        output.append(ROW_OPEN);
        output.append(HEADER_OPEN+"Cost"+HEADER_CLOSE);
        if (!results.isEmpty()){
            Collections.sort(results.get(0).getIndex());
            for (Integer key : results.get(0).getIndex()){
                output.append(HEADER_OPEN+key+HEADER_CLOSE);
            }
        }
        output.append(ROW_CLOSE+NEWLINE);
        
        results.forEach(result -> {
            output.append(TAB+ROW_OPEN+COLUMN_OPEN);
            output.append(result.getCostFactor()+COLUMN_CLOSE);

            Collections.sort(result.getIndex());
            for (Integer key : result.getIndex()){
                output.append(COLUMN_OPEN+result.getResults().get(key)+COLUMN_CLOSE);
            }
            output.append(ROW_CLOSE+NEWLINE);
        });
        
        output.append(TABLE_CLOSE);
        output.append(NEWLINE);
        return output.toString();
    }
}
