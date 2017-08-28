package com.alexander.scratchpad.crypto.bcrypt.results.formatters;

import java.util.concurrent.TimeUnit;

/**
 * Created by Alexander on 28/08/2017.
 */
public class Formatting {
    public static String getFormattedTime(long millis){
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
