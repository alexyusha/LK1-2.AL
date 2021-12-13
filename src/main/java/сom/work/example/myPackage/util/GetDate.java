package сom.work.example.myPackage.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GetDate {
    public static Calendar getDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        return calendar;
    }

    public static Calendar getDate(String str) {
        Calendar calendar = Calendar.getInstance();
        String[] date = str.split("-");
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
        calendar.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
        calendar.set(Calendar.YEAR, Integer.parseInt(date[0]));
        return calendar;
    }

    public static String getDateForDB(int day, int month, int year) {
        Calendar calendar = getDate(day, month, year);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");
        return simpleDateFormat.format(calendar.getTime());
    }
}
