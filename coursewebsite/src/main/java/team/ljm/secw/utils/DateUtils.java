package team.ljm.secw.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String dateToStrDateTime(Date date, String dateFormat){
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        String dateTime = df.format(date);
        return dateTime;
    }

}