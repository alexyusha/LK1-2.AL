import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetDate {
    public static  Date getDate(int day, int month, int year){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    public static int getDay(String str){
        String[] time = str.split("\\.");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(time[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(time[1])-1);
        calendar.set(Calendar.YEAR, Integer.parseInt(time[2]));
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
