package com.alexander.scratchpad.crypto.bcrypt;

import java.util.List;

/**
 * Created by Alexander on 16/08/2017.
 */
public interface DictionaryResultsFormatter {
    
    String format(List<DictionaryResult> results);
}
