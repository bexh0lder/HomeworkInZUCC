public class Time {
    public static void main(String args[]){
        Time t1 = new Time();
        System.out.println("Hour: " + t1.hour + " Minute: " + t1.minute + " Second: " + t1.second);
        Time t2 = new Time(555550000);
        System.out.println("Hour: " + t2.hour + " Minute: " + t2.minute + " Second: " + t2.second);
    }
    private long hour;
    private long minute;
    private long second;

    Time(){
        long totalSeconds = System.currentTimeMillis() / 1000;
        long totalMinutes = totalSeconds / 60;
        long totalHours = totalMinutes / 60;
        this.hour = totalHours % 24;
        this.minute = totalMinutes % 60;
        this.second = totalSeconds % 60;
    }
    Time(long elapsedTime){
        long totalSeconds = elapsedTime / 1000;
        long totalMinutes = totalSeconds / 60;
        long totalHours = totalMinutes / 60;
        this.hour = totalHours % 24;
        this.minute = totalMinutes % 60;
        this.second = totalSeconds % 60;
    }
    Time(int newHour, int newMinute, int newSecond){
        hour = newHour;
        minute = newMinute;
        second = newSecond;
    }
    long getHour(){
        return hour;
    }
    long getMinute(){
        return minute;
    }
    long getSecond(){
        return second;
    }
    void setTime(long elapsedTime){

    }

}
