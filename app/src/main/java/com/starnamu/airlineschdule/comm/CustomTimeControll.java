package com.starnamu.airlineschdule.comm;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by starnamu on 2015-08-26.
 */
public class CustomTimeControll {

    public CustomTimeControll() {
    }

    public int userChoiceTime(long customTime) {
        long time1 = System.currentTimeMillis();

        long time2 = time1 - customTime;
        Date date1 = new Date(time1);
        SimpleDateFormat CurTimeFormat1 = new SimpleDateFormat("HHmm");
        String strCurTime1 = CurTimeFormat1.format(date1);

        Date date2 = new Date(time2);
        SimpleDateFormat CurTimeFormat2 = new SimpleDateFormat("HHmm");
        String strCurTime2 = CurTimeFormat2.format(date2);

        if (time2 <= 0) {
            return Integer.parseInt(strCurTime1);
        }
        return Integer.parseInt(strCurTime2);
    }
}
