import java.util.Calendar;

public class GetDate {
    public static  Calendar getDate(int day, int month, int year){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.YEAR, year);
        return calendar;
    }
}
