import java.util.Calendar;

public class GetDate {
    public static  Calendar getDate(int day, int month, int year){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.YEAR, year);
        return calendar;
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
