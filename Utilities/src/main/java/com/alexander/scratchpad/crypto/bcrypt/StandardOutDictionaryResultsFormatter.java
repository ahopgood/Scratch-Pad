package com.alexander.scratchpad.crypto.bcrypt;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alexander on 21/08/2017.
 */
public class StandardOutDictionaryResultsFormatter implements DictionaryResultsFormatter {
    public static int columnWidth = 30;
    public static final String NEWLINE  = "\n";
    public static final String TAB      = "\t";
    
    public static final String HEADER_OPEN     = "|";
    public static final String HEADER_CLOSE    = "|";

    public static final String COLUMN_OPEN  = "|";
    public static final String COLUMN_CLOSE = "|";
    @Override
    public String format(List<DictionaryResult> results) {
        StringBuilder output = new StringBuilder();

        output.append(HEADER_OPEN+pad("Cost")+HEADER_CLOSE);
        if (!results.isEmpty()){
            Collections.sort(results.get(0).getIndex());
            for (Integer key : results.get(0).getIndex()){
                output.append(pad(key+"")+HEADER_CLOSE);
            }
        }
//        for ()
        output.append(NEWLINE);

        results.forEach(result -> {
            output.append(COLUMN_OPEN);
            output.append(pad(result.getCostFactor()+"")+COLUMN_CLOSE);

            Collections.sort(result.getIndex());
            for (Integer key : result.getIndex()){
                output.append(pad(getFormatted(result.getResults().get(key))+"")+COLUMN_CLOSE);
            }
            output.append(NEWLINE);
        });
        output.append(NEWLINE);
        return output.toString();
    }
    
    public String border(){
        return pad("", "-");
    }
    
    public String pad(String input){
        return pad(input, " ");
    }
    
    public String pad(String input, String padChars){
        int remainingChars = this.columnWidth - input.length();
        if (remainingChars > 0){
            input = input.concat(addWhitespace(remainingChars, padChars));
        }
        return input;
        
    }
    
    public String addWhitespace(int length, String padChars){
        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < length; i++){
            padding.append(padChars);
        }
        return padding.toString();
    }
    

    
    public String getFormatted(long millis){
        //Conversion from millis to total of each measurement of time
        long secs = TimeUnit.MILLISECONDS.toSeconds(millis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        
        long remainingMinutes = minutes - TimeUnit.HOURS.toMinutes(hours);
        long remainingSeconds = secs - TimeUnit.MINUTES.toSeconds(remainingMinutes) - TimeUnit.HOURS.toSeconds(hours);
        long remainingMillis = millis - TimeUnit.SECONDS.toMillis(remainingSeconds) - TimeUnit.MINUTES.toMillis(remainingMinutes) - TimeUnit.HOURS.toMillis(hours);
                
        return String.format("%d hr, %d min, %d sec, %d ms",
                hours,
                remainingMinutes,
                remainingSeconds,
                remainingMillis
        );
    }
}
