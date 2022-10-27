import java.util.*;

public class MyDate {
    public static void main(String argus[]) {
        MyDate t1 = new MyDate();
        System.out.println("The current time is:");
        System.out.println("year: " + t1.getYear());
        System.out.println("month: " + t1.getMonth());
        System.out.println("day: " + t1.getDay());
        MyDate t2 = new MyDate(561555550000L);
        System.out.println("561555550000L millisecond is:");
        System.out.println("year: " + t2.getYear());
        System.out.println("month: " + t2.getMonth());
        System.out.println("day: " + t2.getDay());
        MyDate t3 = new MyDate(34355555133101L);
        System.out.println("34355555133101L millisecond is:");
        System.out.println("year: " + t3.getYear());
        System.out.println("month: " + t3.getMonth());
        System.out.println("day: " + t3.getDay());
    }
    private int year;
    private int month;
    private int day;
    MyDate(){
        GregorianCalendar date = new GregorianCalendar();
        year = date.get(Calendar.YEAR);
        month = date.get(Calendar.MONTH);
        day = date.get(Calendar.DAY_OF_MONTH);
    }
    MyDate(long elapsedTime){
        GregorianCalendar date = new GregorianCalendar();
        date.setTimeInMillis(elapsedTime);
        year = date.get(Calendar.YEAR);
        month = date.get(Calendar.MONTH);
        day = date.get(Calendar.DAY_OF_MONTH);
    }
    MyDate(int newYear, int newMonth, int newDay){
        year = newYear;
        month = newMonth;
        day = newDay;
    }
    int getYear() {
        return year;
    }
    int getMonth() {
        return month;
    }
    int getDay() {
        return day;
    }
    void setYear(int newYear) {
        year = newYear;
    }
    void setMonth(int newMonth) {
        month = newMonth;
    }
    void setDay(int newDay) {
        day = newDay;
    }
}