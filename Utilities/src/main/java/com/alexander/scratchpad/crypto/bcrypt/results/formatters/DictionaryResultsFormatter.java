package com.alexander.scratchpad.crypto.bcrypt.results.formatters;

import com.alexander.scratchpad.crypto.bcrypt.results.DictionaryResult;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alexander on 16/08/2017.
 */
public interface DictionaryResultsFormatter {
    
    String format(List<DictionaryResult> results);
}
