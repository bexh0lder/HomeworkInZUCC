import java.util.Scanner;
import java.util.Calendar;

public class DisplayCalendar {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input the year:");
        int year = input.nextInt();
        System.out.println("Please input the month:");
        int month = input.nextInt();

        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, 1);

        int dayOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int dayWeek = c.get(Calendar.DAY_OF_WEEK);
        System.out.println("日\t一\t二\t三\t四\t五\t六");
        for (int i = 1; i < dayWeek; i++) {
            System.out.print("\t");
        }
        for (int i = 0; i < dayOfMonth; i++) {
            System.out.print(i + 1 + "\t");
            if ((dayWeek + i) % 7 == 0) System.out.println();
        }
    }
}
