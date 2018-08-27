package com.becoblohm.cr.crjpa.controller;

import java.util.Calendar;
import java.util.Date;

public class JPAUtils {

    public static Date cleanDate(Date date, boolean isStartingDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        if (isStartingDay) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 1);
        } else {
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
        }

        return cal.getTime();
    }
}
